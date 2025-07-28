package view.pieces;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.board.Board;
import model.pieces.Piece;

/**
 * A bábuk textúrájáért felelős osztály.
 * @author Y89Q16
 */
public class PieceView {

    Piece piece;

    private BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(this.getClass().getResourceAsStream("/pieces.png")); 
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    Image sprite;

    private final int sheetScale = sheet.getWidth() / 6;


    /**
     * Ctor, a paraméterként kapott Piecere hoz létre egy textúrát.
     * @param piece - Piece
     */
    public PieceView(Piece piece) {
        this.piece = piece;

        if(piece.getName().equals("Bishop")) {
            this.setSprite(this.getSheet().getSubimage(2 * this.getSheetScale(), piece.isWhite() ? 0 : this.getSheetScale(), this.getSheetScale(), this.getSheetScale())
            .getScaledInstance(Board.TILE_SIZE, Board.TILE_SIZE, BufferedImage.SCALE_SMOOTH));

        } else if(piece.getName().equals("Queen")) {
            this.setSprite(this.getSheet().getSubimage(1 * this.getSheetScale(), piece.isWhite() ? 0 : this.getSheetScale(), this.getSheetScale(), this.getSheetScale())
            .getScaledInstance(Board.TILE_SIZE, Board.TILE_SIZE, BufferedImage.SCALE_SMOOTH));
            
        } else if(piece.getName().equals("King")) {
            this.setSprite(this.getSheet().getSubimage(0 * this.getSheetScale(), piece.isWhite() ? 0 : this.getSheetScale(), this.getSheetScale(), this.getSheetScale())
            .getScaledInstance(Board.TILE_SIZE, Board.TILE_SIZE, BufferedImage.SCALE_SMOOTH));
            
        } else if(piece.getName().equals("Pawn")) {
            this.setSprite(this.getSheet().getSubimage(5 * this.getSheetScale(), piece.isWhite() ? 0 : this.getSheetScale(), this.getSheetScale(), this.getSheetScale())
            .getScaledInstance(Board.TILE_SIZE, Board.TILE_SIZE, BufferedImage.SCALE_SMOOTH));
            
        } else if(piece.getName().equals("Rook")) {
            this.setSprite(this.getSheet().getSubimage(4 * this.getSheetScale(), piece.isWhite() ? 0 : this.getSheetScale(), this.getSheetScale(), this.getSheetScale())
            .getScaledInstance(Board.TILE_SIZE, Board.TILE_SIZE, BufferedImage.SCALE_SMOOTH));
            
        } else if(piece.getName().equals("Knight")) {
            this.setSprite(this.getSheet().getSubimage(3 * this.getSheetScale(), piece.isWhite() ? 0 : this.getSheetScale(), this.getSheetScale(), this.getSheetScale())
            .getScaledInstance(Board.TILE_SIZE, Board.TILE_SIZE, BufferedImage.SCALE_SMOOTH));
        }
    }
    
    /**
     * Visszaadja a sheetet.
     * @return BufferedImage
     */
    public BufferedImage getSheet() {
        return sheet;
    }

    /**
     * Beállítja a sheetet.
     * @param sheet - BufferedImage
     */
    public void setSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Visszaadja a textúrához használt kép/sheet szélességét, ami alapján azt spritokra osztjuk.
     * @return int
     */
    public int getSheetScale() {
        return sheetScale;
    }

    /**
     * Visszaadja a sprite_ot.
     * @return Image
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Beállítja a sprite_ot
     * @param sprite - Image
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * Megjeleníti a textúrát a g2d Graphics2D osztály segítségével a tartalmazott bábu helyére.
     * @param g2d - Graphics2D
     */
    public void paint(Graphics2D g2d) {
        g2d.drawImage(sprite, piece.getCoords().getxPos(), piece.getCoords().getyPos(), null);
    }
}
