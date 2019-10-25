package WithPanels;

/**
 * Questa classe rappresenta un modello astratto di una matrice da stampare.
 *
 * @author Andr Da Silva
 * @version 13.04.19
 */
public class MatrixModel {

    /**
     * Numero di righe della matrice.
     */
    private int rows;

    /**
     * Valore della colonna piu grande della matrice.
     */
    private int cols;

    /**
     * Margine interno.
     */
    private int margin;

    /**
     * Altezza del contenitore.
     */
    private int hContainer;

    /**
     * Larghezza del contenitore.
     */
    private int wContainer;

    /**
     * Proporzione X della matrice.
     */
    private int ratioX;

    /**
     * Proporzione Y della matrice.
     */
    private int ratioY;

    /**
     * Costruttore per instanziare un oggetto di tipo MatrixModel.
     *
     * @param rows Numero di righe della matrice.
     * @param cols Valore della colonna piu grande della matrice.
     * @param margin Margine interno.
     * @param hContainer Altezza contenitore.
     * @param wContainer Larghezza contenitore.
     * @param ratioX Proporzione X della matrice.
     * @param ratioY Proporzione Y della matrice.
     */
    public MatrixModel(int rows, int cols, int margin, int hContainer, int wContainer, int ratioX, int ratioY) {

        this.rows = rows;
        this.cols = cols;
        this.margin = margin;
        this.hContainer = hContainer;
        this.wContainer = wContainer;
        this.ratioX = ratioX;
        this.ratioY = ratioY;
        this.hContainer -= (this.margin * 2);
        this.wContainer -= (this.margin * 2);

    }

    /**
     * Ritorna un array contenente la larghezza e l'altezza di una cella della
     * matrice (0 = larghezza, 1 = altezza).
     *
     * @return cs array contenente larghezza e altezza.
     */
    public double[] getCellSize() {

        double[] cs = new double[2];

        if ((this.wContainer) / this.cols > (this.hContainer) / this.rows) { // adattamento in altezza

            cs[0] = (this.hContainer) / this.rows;

        } else { // adattamento in larghezza

            cs[0] = (this.wContainer) / this.cols;
        }

        cs[1] = (cs[0] * ratioY) / ratioX;
        if (cs[1] > this.hContainer) {
            cs[1] = cs[1] - (cs[1] - this.hContainer);
        }
        cs[0] = cs[0] - this.margin * 2;
        return cs;
    }

    /**
     * Ritorna un array contenente le coordinate X e Y affinche la matrice sia
     * centrata (0 = x, 1 = y).
     *
     * @return dxy array contenente le coordinate X e Y per il centramento.
     */
    public double[] getDXYSize() {

        double[] dxy = new double[2]; //0 = x, 1 = y

        if ((this.wContainer) / this.cols > (this.hContainer) / this.rows) { // adattamento in altezza

            dxy[1] = this.margin;
            dxy[0] = this.margin + (this.wContainer - (this.cols * ((this.hContainer / this.rows)))) / 2 + this.margin;

        } else { //adattamento in larghezza

            dxy[1] = this.margin + (this.hContainer - (this.rows * (this.wContainer / this.cols))) / 2;
            dxy[0] = this.margin;
        }
        return dxy;
    }
}
