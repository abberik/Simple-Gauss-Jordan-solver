package solver;

public class Lasare {

	public static void main(String[] args) {

		try{	//se om anvandaren fattat hur man anvander programmet.
			 
			String filnamn = args[0]; // las namnet
			new Lasare(filnamn); //starta programmet
			
		}catch(Exception ex){
			System.out.println("Syntax: Java -jar Program.jar matrix.csv"); // anvandaren fattade inte, meddelar...
		}
	}

	private Ekvation ekvation; // representerar en ekvation pa matrisnotation
	
	public Lasare(String filnamn) {
		
		ekvation = new Ekvation(filnamn); //las in ekvation
		
		ekvation.skriv_ut(); //skriv ut ekvation
		
		los(); // borja losa
		
	}
	
	public void los(){
		
		
		System.out.println("Genomfor en Gauss elimination");
		gauss_elimination();
		
		System.out.println("Anvander Jordans metod.");
		jordans_metod();
	}
	
	private void jordans_metod(){
		
		double[][] matris = ekvation.getMatris();	//hamta matrisen
		
		for(int i = matris.length-1; i >= 0; i--){ // ga diagonalt tillbaka fran den nedre hogra variabeln
			
			matris = los_kolumn(matris, i, i); // Subtrahera bort nodvandigt antal av denna ekvation fran samtliga andra
			
			skriv_ut_matris(matris); // skriv ut matrisen
		
		}
		
		ekvation.setMatris(matris); //lamna tillbaka matrisen.
	}
	
	private double[][] los_kolumn(double[][] m, int rad, int kolumn){
		
		double[][] matris = m;	 //hamta matrisen
		
		double antal = matris[rad][kolumn];	//hitta antalet av den variabeln som ska subtraheras
		
		//dividera hela raden med detta nummer
		
		for(int a = 0; a<matris[rad].length ; a++) matris[rad][a] = (matris[rad][a] / antal);				
		
		double varde = matris[rad][matris[rad].length - 1]; //hitta vardet av variabeln
		
		//ga uppat och subtrahera bort denna rad fran alla utom "losnings" raden
		for(int a = rad; a>=0; a--){ 
			
			//a representerar raden
			
			//hitta antalet av denna kolumn 
			
			antal = matris[a][kolumn];
						
			//om det inte ar raden med losningen.
			
			if(a != rad){
				
				//satt antalet av denna kolumn till 0 
				
				matris[a][kolumn] = 0;
			
				//subtrahera produkten av varde och antalet fran summan
				
				matris[a][matris[a].length-1] -= (	antal * varde	);
			}
			
			
			
		}
		
		
		return matris; // lamna tillbaka matrisen
		
	}
	
	private void gauss_elimination(){
		
		double[][] matris = ekvation.getMatris(); 	//hamta matrisen
		System.out.println("Ordnar en ledande etta.");	
		matris = ordna_ledande_etta(matris); //ordna den ledande ettan.
		
		skriv_ut_matris(matris); // skriv ut matrisen
		
		System.out.println("Eliminerar varden."); // genomfor elimination
		for(int i = 0; i < matris.length-1; i++){	//for varje kolumn
			System.out.println("Eliminerar rad " + i + "."); 
			eliminera(matris, i, i); //eliminera rad
			skriv_ut_matris(matris); //skriv ut matris
			
			
		}
		System.out.println("Fardig med Gauss elimination.");
		skriv_ut_matris(matris);	//skriv ut matrisen
		ekvation.setMatris(matris); //spara den i ekvationsklassen
		
	}

	private double[][] eliminera(double[][] mat,int initial_rad,int initial_kolumn){
		
		double[][] matris = mat;	//hamta matris
		
		double initialt_varde = matris[initial_rad][initial_kolumn];	//hitta koefficienten framfor variabeln vi vill eliminera.
		
		for(int a = initial_rad+1; a < matris.length; a++){ //Utfor detta pa alla rader.
			
			double ganger = (matris[a][initial_kolumn] / initialt_varde); // subtrahera bort koefficienten.
			
			for(int b = initial_kolumn; b < matris[a].length; b++){ // Subtrahera bort pa varje rad.
				
				matris[a][b] -= (matris[initial_rad][b] * ganger); //Subtrahera bort det.
				
			}
			
			
			
		}
		
		
		
		return matris; // lamna tillbaka matrisen.
	}
	
	private double[][] ordna_ledande_etta(double[][] mat){

		/*
		 * Se till att den forsta kolumnen pa den forsta raden innehaller en etta
		 */
		 double[][] matris = mat;
		double ledande = matris[0][0];
		
		for(int i = 0; i < matris[0].length; i++){
			
			matris[0][i] = (matris[0][i] / ledande);
			
		}
		
		return matris; //lanba tillbaka
		
	}
	
	private void skriv_ut_matris(double[][] matris){
		
		/*
		 * r star for rad
		 * c star for kolumn
		 * 
		 */
		
		//Loopa igenom varje rad och kolumn och printa.
		
		for(int r = 0; r < matris.length; r++){ 
			
			System.out.print("\n");
			
			for(int c = 0; c < matris[0].length; c++){
				
				System.out.print(matris[r][c] + "\t");
				
			}
			
			
			
		}
		System.out.print("\n\n");
		
	}
	
}
