import java.awt.geom.Ellipse2D;

/**
 * Un cercle qui peut etre manipule et qui se dessine sur une toile (canvas).
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  mod.by Denis BUREAU
 * @version 2006.03.30/2012.02.15
 */

public class Cercle
{
    private int     aDiametre;
    private int     aXPosition;
    private int     aYPosition;
    private String  aCouleur;
    private boolean aEstVisible;
    
    /**
     * Cree un nouveau cercle avec les position et couleur par defaut.
     */
    public Cercle()
    {
        this(30, 20, 60, "blue");
    } // Cercle()
    
    /**
     * Cree un nouveau cercle avec les position et couleur par defaut.
     */
    public Cercle(final int pDiametre, final int pXPosition, final int pYPosition, final String pCouleur)
    {
        this.aDiametre=  pDiametre;
        this.aXPosition= pXPosition;
        this.aYPosition= pYPosition;
        this.aCouleur=   pCouleur;
        this.aEstVisible= false;
        
        this.rendVisible();
    } // Cercle()
    
    /**
     * Returns the position of the circle
     * @return int circle's position
     */
    public int getPosition(){
        return 1000 * this.aXPosition + this.aYPosition;
    }
    
    /**
     * Rend ce cercle visible.
     */
    public void rendVisible()
    {
        this.aEstVisible= true;
        this.dessine();
    } // rendVisible()
    
    /**
     * Rend ce cercle invisible.
     */
    public void rendInvisible()
    {
        this.efface();
        this.aEstVisible= false;
    } // rendInvisible()
    
    /**
     * Deplace le cercle de quelques pixels vers la droite.
     */
    public void vaDroite()
    {
        this.depHorizontal( 20 );
    } // vaDroite()

    /**
     * Deplace le cercle de quelques pixels vers la gauche.
     */
    public void vaGauche()
    {
        this.depHorizontal( -20 );
    } // vaGauche()

    /**
     * Deplace le cercle de quelques pixels vers le haut.
     */
    public void vaHaut()
    {
        this.depVertical( -20 );
    } // vaHaut()

    /**
     * Deplace le cercle de quelques pixels vers le bas.
     */
    public void vaBas()
    {
        this.depVertical( 20 );
    } // vaBas()

    /**
     * Deplace le cercle horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     */
    public void depHorizontal( final int pDistance )
    {
        this.efface();
        this.aXPosition= this.aXPosition + pDistance;
        this.dessine();
    } // depHorizontal()

    /**
     * Deplace le cercle verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     */
    public void depVertical( final int pDistance )
    {
        this.efface();
        this.aYPosition= this.aYPosition + pDistance;
        this.dessine();
    } // depVertical()

    /**
     * Deplace lentement le carre horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     */
    public void depLentHorizontal( final int pDistance )
    {
         int vDelta = 0; // le pas unitaire de deplacement
 
         if ( pDistance < 0 ) {
             vDelta=-1;
         }
         else if (pDistance > 0) {
             vDelta= 1;
         } // if

        // ecrire ici le deplacement recursif :
        if(vDelta != 0){
            this.depHorizontal( vDelta );
            this.depLentHorizontal(pDistance - vDelta);
        }
    } // depLentHorizontal()

    /**
     * Deplace lentement le carre verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     */
    public void depLentVertical( final int pDistance )
    {
         int vDelta = 0; // le pas unitaire de deplacement
 
         if ( pDistance < 0 ) {
             vDelta=    -1;
         }
         else if (pDistance > 0) {
             vDelta=    1;
         } // if

        // ecrire ici le deplacement recursif :
        if(vDelta != 0){
            this.depVertical( vDelta );
            this.depLentVertical(pDistance - vDelta);
        }
    } // depLentVertical()

    /**
     * Remplace l'ancienne taille par la nouvelle (en pixels).
     * La taille doit etre >= 0.
     * @param pNouvDiametre diametre souhaite pour ce cercle
     */
    public void changeTaille( final int pNouvDiametre )
    {
        this.efface();
        this.aDiametre= pNouvDiametre;
        this.dessine();
    } // changeTaille()


    /**
     * Change la couleur. Les couleurs valides sont
     * "red", "yellow", "blue", "green", "magenta" and "black".
     * @param pNewColor couleur souhaitee pour ce cercle
     */
    public void changeCouleur( final String pNouvCouleur )
    {
        this.aCouleur= pNouvCouleur;
        this.dessine();
    } // changeCouleur()

    /**
     * Dessine sur l'ecran le cercle avec ses specifications courantes
     */
    private void dessine()
    {
        if ( this.aEstVisible ) {
            Canvas vCanvas; // classe compliquee a ne pas regarder pour le moment
            vCanvas= Canvas.getCanvas();
            vCanvas.draw(this, this.aCouleur,
                         new Ellipse2D.Double( this.aXPosition, this.aYPosition, 
                                               this.aDiametre, this.aDiametre ) );
            vCanvas.waitALittle();
        } // if
    } // dessine()

    /**
     * Efface le cercle sur l'ecran.
     */
    private void efface()
    {
        if ( this.aEstVisible ) {
            Canvas vCanvas;
            vCanvas= Canvas.getCanvas();
            vCanvas.erase( this );
        } // if
    } // efface()

} // Cercle
