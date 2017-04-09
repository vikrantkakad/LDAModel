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

import org.kohsuke.args4j.*;

public class LDA {
	
	public static void main(String args[]){
		LDACmdOption option = new LDACmdOption();
		CmdLineParser parser = new CmdLineParser(option);
		
		try {
			if (args.length == 0){
				showHelp(parser);
				return;
			}
			
			parser.parseArgument(args);
			
			if (option.est || option.estc){
				Estimator estimator = new Estimator();
				estimator.init(option);
				estimator.estimate();
			}
			else if (option.inf){
				Inferencer inferencer = new Inferencer();
				inferencer.init(option);
				
				Model newModel = inferencer.inference();
			
				for (int i = 0; i < newModel.phi.length; ++i){
					//phi: K * V
					System.out.println("-----------------------\ntopic" + i  + " : ");
					for (int j = 0; j < 10; ++j){
						System.out.println(inferencer.globalDict.id2word.get(j) + "\t" + newModel.phi[i][j]);
					}
				}
			}
		}
		catch (CmdLineException cle){
			System.out.println("Command line error: " + cle.getMessage());
			showHelp(parser);
			return;
		}
		catch (Exception e){
			System.out.println("Error in main: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}
	
	public static void showHelp(CmdLineParser parser){
		System.out.println("LDA [options ...] [arguments...]");
		parser.printUsage(System.out);
	}
	
}
