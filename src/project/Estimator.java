/* Copyright (C) 2017 Vikrant Kakad

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see https://www.gnu.org/licenses/gpl.html
*/
package project;

import java.io.File;

public class Estimator {

	// output model
	protected Model trnModel;
	LDACmdOption option;

	public boolean init(LDACmdOption option) {
		this.option = option;
		trnModel = new Model();

		if (option.est) {
			if (!trnModel.initNewModel(option))
				return false;
			trnModel.data.localDict.writeWordMap(option.dir + File.separator + option.wordMapFileName);
		} else if (option.estc) {
			if (!trnModel.initEstimatedModel(option))
				return false;
		}

		return true;
	}

	public void estimate() {
		System.out.println("Sampling " + trnModel.niters + " iteration!");

		int lastIter = trnModel.liter;
		for (trnModel.liter = lastIter + 1; trnModel.liter < trnModel.niters + lastIter; trnModel.liter++) {
			System.out.println("Iteration " + trnModel.liter + " ...");

			// for all z_i
			for (int m = 0; m < trnModel.M; m++) {
				for (int n = 0; n < trnModel.data.docs[m].length; n++) {
					// z_i = z[m][n]
					// sample from p(z_i|z_-i, w)
					int topic = sampling(m, n);
					trnModel.z[m].set(n, topic);
				} // end for each word
			} // end for each document

			if (option.savestep > 0) {
				if (trnModel.liter % option.savestep == 0) {
					System.out.println("Saving the model at iteration " + trnModel.liter + " ...");
					computeTheta();
					computePhi();
					trnModel.saveModel("model-" + Conversion.ZeroPad(trnModel.liter, 5));
				}
			}
		} // end iterations

		System.out.println("Gibbs sampling completed!\n");
		System.out.println("Saving the final model!\n");
		computeTheta();
		computePhi();
		trnModel.liter--;
		trnModel.saveModel("model-final");
	}

	/**
	 * Do sampling
	 * 
	 * @param m
	 *            document number
	 * @param n
	 *            word number
	 * @return topic id
	 */
	public int sampling(int m, int n) {
		// remove z_i from the count variable
		int topic = trnModel.z[m].get(n);
		int w = trnModel.data.docs[m].words[n];

		trnModel.nw[w][topic] -= 1;
		trnModel.nd[m][topic] -= 1;
		trnModel.nwsum[topic] -= 1;
		trnModel.ndsum[m] -= 1;

		double Vbeta = trnModel.V * trnModel.beta;
		double Kalpha = trnModel.K * trnModel.alpha;

		// do multinominal sampling via cumulative method
		for (int k = 0; k < trnModel.K; k++) {
			trnModel.p[k] = (trnModel.nw[w][k] + trnModel.beta) / (trnModel.nwsum[k] + Vbeta)
					* (trnModel.nd[m][k] + trnModel.alpha) / (trnModel.ndsum[m] + Kalpha);
		}

		// cumulate multinomial parameters
		for (int k = 1; k < trnModel.K; k++) {
			trnModel.p[k] += trnModel.p[k - 1];
		}

		// scaled sample because of unnormalized p[]
		double u = Math.random() * trnModel.p[trnModel.K - 1];

		for (topic = 0; topic < trnModel.K; topic++) {
			if (trnModel.p[topic] > u) // sample topic w.r.t distribution p
				break;
		}

		// add newly estimated z_i to count variables
		trnModel.nw[w][topic] += 1;
		trnModel.nd[m][topic] += 1;
		trnModel.nwsum[topic] += 1;
		trnModel.ndsum[m] += 1;

		return topic;
	}

	public void computeTheta() {
		for (int m = 0; m < trnModel.M; m++) {
			for (int k = 0; k < trnModel.K; k++) {
				trnModel.theta[m][k] = (trnModel.nd[m][k] + trnModel.alpha)
						/ (trnModel.ndsum[m] + trnModel.K * trnModel.alpha);
			}
		}
	}

	public void computePhi() {
		for (int k = 0; k < trnModel.K; k++) {
			for (int w = 0; w < trnModel.V; w++) {
				trnModel.phi[k][w] = (trnModel.nw[w][k] + trnModel.beta)
						/ (trnModel.nwsum[k] + trnModel.V * trnModel.beta);
			}
		}
	}
}
