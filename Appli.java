import java.awt.image.Raster;

import fr.unistra.pelican.ByteImage;
import fr.unistra.pelican.Image;
import fr.unistra.pelican.algorithms.io.ImageLoader;
import fr.unistra.pelican.algorithms.visualisation.Viewer2D;
import fr.unistra.pelican.util.Pixel;

public class Appli {
	
	public static Image couleurVersNiveauGris(Image img) { //Transformer les couleurs de l'images en image de niveaux de gris
		int largeur = img.getXDim(); //Dimensions de l'image. largeur +hauteur
		int hauteur=img.getYDim();
		ByteImage result=new ByteImage(largeur,hauteur,1,1,3);//Instanciation de l'image qu'on va transformé.
		int r,g,b;
		for(int x=0;x<img.getXDim();x++) { //On parcourt chaque pixels de l'image.
			for(int y=0;y<img.getYDim();y++){ 
				r=img.getPixelXYBByte(x, y, 0);//Pour ensuite associer un niveau de rouge vert et bleu.
				g=img.getPixelXYBByte(x, y, 1);
				b=img.getPixelXYBByte(x, y, 2);
				int v= (r+g+b)/3; // v est le niveau de gris qu'on instancie et qu'on calcule.
				result.setPixelXYBByte(x, y, 0, 255-v);  //Affection du niveau de gris à l'image.
			}
		}
		
		return result; // On retourne l'image transformé.
		
	}
	
	
	public static Image Binarisation(Image img) { //
		int seuil=200; //tous les pixels qui sont supérieur à un seuil s sont blanc, les autres sont noir.
		int largeur = img.getXDim();
		int hauteur=img.getYDim();
		ByteImage result=new ByteImage(largeur,hauteur,1,1,3);
		int r,g,b;
		for(int x=0;x<img.getXDim();x++) { //si p(i,j) représente la valeur du pixel au point (i,j), on balaye toute l'image et on compte le nombre de fois ou un niveau de gris apparait.
			for(int y=0;y<img.getYDim();y++){ 
				r=img.getPixelXYBByte(x, y, 0); 
				g=img.getPixelXYBByte(x, y, 1);
				b=img.getPixelXYBByte(x, y, 2);
				//int v= (r)/3;
				if(r<=seuil)
					result.setPixelXYBByte(x, y, 0, 0);
				else
					result.setPixelXYBByte(x, y, 0, 255);
				
			}
			
		}
		return result;
	}
	
	/*
	 *	Je n'ai pas réussi à coder la méthode histogramme mais j'ai compris qu'il faut définir
			des procédures pour récuppérer que la composante bleue, que la rouge et que la verte.
			Ensuite, à chaque fois que l'on prends un pixel, on étudies sa valeur rouge(puis verte, puis bleue) et on incrémente la valeur équivalente dans un tableau.
			Par ex: si on  trouves la valeur Rouge=130, on incrémentes la case correspondante: TableauRouge[130]++ */
	

	
	//public static Image historigramme(Image img) {
		
		
	
	//int tab[]=new int [256];
		//int largeur = img.getXDim();
		//int hauteur=img.getYDim();
		//Pixel p=img.getPixelXYBByte(x, y, b);
		
//		 Raster trame = img.getCenter();
//		 int[] rgb = new int[3];
//	      int maximum = 0;
//	      for (int y=0; y<img.getHeight(); y++)
//	         for (int x=0; x<image.getWidth(); x++) {
//	            trame.getPixel(x, y, rgb);
//	            rouge[rgb[0]]++;
//	            vert[rgb[1]]++;
//	            bleu[rgb[2]]++;
//	         }     
	//}
	
	
	public static void main(String[] args) {
		Image test=ImageLoader.exec("images/eiffel.jpg"); // On exécute l'image.
		//Image imgNG=couleurVersNiveauGris(test);
		//imgNG.setColor(true);
		Image imgB=Binarisation(test);
		Viewer2D.exec(imgB);
	}

}
