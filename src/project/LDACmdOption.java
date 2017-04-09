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

public class LDACmdOption {
	
	@Option(name="-est", usage="Specify whether we want to estimate model from scratch")
	public boolean est = false;
	
	@Option(name="-estc", usage="Specify whether we want to continue the last estimation")
	public boolean estc = false;
	
	@Option(name="-inf", usage="Specify whether we want to do inference")
	public boolean inf = true;
	
	@Option(name="-dir", usage="Specify directory")
	public String dir = "";
	
	@Option(name="-dfile", usage="Specify data file")
	public String dfile = "";
	
	@Option(name="-model", usage="Specify the model name")
	public String modelName = "";
	
	@Option(name="-alpha", usage="Specify alpha")
	public double alpha = -1.0;
	
	@Option(name="-beta", usage="Specify beta")
	public double beta = -1.0;
	
	@Option(name="-ntopics", usage="Specify the number of topics")
	public int K = 100;
	
	@Option(name="-niters", usage="Specify the number of iterations")
	public int niters = 1000;
	
	@Option(name="-savestep", usage="Specify the number of steps to save the model since the last save")
	public int savestep = 100;
	
	@Option(name="-twords", usage="Specify the number of most likely words to be printed for each topic")
	public int twords = 100;
	
	@Option(name="-withrawdata", usage="Specify whether we include raw data in the input")
	public boolean withrawdata = false;
	
	@Option(name="-wordmap", usage="Specify the wordmap file")
	public String wordMapFileName = "wordmap.txt";
}
