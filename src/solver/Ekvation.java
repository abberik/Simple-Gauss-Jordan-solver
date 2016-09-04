package solver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ekvation {

	/*
	 *
	 * Denna klass innehaller ett ekvationsystem pa matrisform.
	 * Nagon ordentlig parser (syftar till att lasa ekavtionen fran vanlig form) finns ej.
	 * Ekvationen lases fran en strikt formaterad .csv fil.
	 * 
	 */
	
	//Exempel pa innehall i textfil
	
	/*
	 * 
	 * a,b,c,d
	 * a,b,c,d
	 * a,b,c,d
	 * 
	 * 
	 * a,b,c formateras da till koefficienterna framfor ekvationerna och d till det efter likhetstecknet.
	 * 
	 */
	
	private double[][] matris; // matrisen
	
	public Ekvation(String filnamn) {
		
		parsa(filnamn); 
		
	}
	
	public void skriv_ut(){
		
		/*
		 * r star for rad
		 * c star for kolumn
		 * 
		 */
		//loopa igenom varje rad och kolumn och printa
		for(int r = 0; r < matris.length; r++){
			
			System.out.print("\n");
			
			for(int c = 0; c < matris[0].length; c++){
				
				System.out.print(matris[r][c] + "\t");
				
			}
			
			
			
		}
		System.out.print("\n\n");

	}
	
	public double[][] getMatris() {
		return matris;
	}

	public void setMatris(double[][] matris) {
		this.matris = matris;
	}

	private void parsa(String filename) {
		ArrayList<String> ra_data;	//Inlast data
		
		ra_data = las_fil(filename); //Las in data
		
		int rader = ra_data.size(); // hitta antal rader
		
		ArrayList<ArrayList<Double>> mat = new ArrayList<ArrayList<Double>>(); // matris i AL form
		
		for(int a = 0; a < rader; a++){ // for varje rad
			
			String rad_data = ra_data.get(a); // plocka ut ratt rad
			
			String[] icke_parsade_varden = rad_data.split(","); // separera dessa varden 
			double[] parsade_varden = new double[icke_parsade_varden.length]; //utvarden
			mat.add(new ArrayList<Double>()); // fixa AL:n
			for(int b = 0; b < icke_parsade_varden.length; b++){ // for varje kolumn
				
				parsade_varden[b] = Double.parseDouble(icke_parsade_varden[b]); //parsa varde
				mat.get(a).add(parsade_varden[b]); //lagg dessa varden i AL:n
				
			}
			
		}
		
		matris = new double[rader][mat.get(0).size()];	//kanske behover vandas
		
		for(int a = 0; a < mat.size(); a++){
			for(int b = 0; b < mat.get(0).size(); b++){
				matris[a][b] = mat.get(a).get(b);
			}
		}
	}

	private ArrayList<String>  las_fil(String filename){
		ArrayList<String> ra_data;	//ra data
		
		try {
			BufferedReader lasare = new BufferedReader(new FileReader(filename)); // lasaren som laser filen
			
			ra_data = new ArrayList<String>(); // initiera ra datan
			
			String current = ""; // temporar variabel
			
			while(true){ //las-loop
				
				current = lasare.readLine(); // las en rad
				
				if(current == null){ //om raden ar tom
					break;
				}else{
					ra_data.add(current); //om raden innehaller nagonting
				}

				
			}
			lasare.close(); // stang lasaren
			return ra_data; // lamna varden
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 			return null;  //eclipse ar gnallig
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; //eclipse ar gnallig
			
		}
		
	}
	
}
