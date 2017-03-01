import javax.swing.JOptionPane;
public class DatabaseException extends Exception {
    private String err;

    /**
     * Constructor for objects of class InvalidMoveException
     */
    public DatabaseException(String err) {
        this.err =err;
    }

    @Override
   public String toString() {
    JOptionPane.showMessageDialog(null, err, "Validation Error", JOptionPane.ERROR_MESSAGE);
       return err;
    }
}
