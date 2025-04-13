import java.util.List;

class DifferentVectorsLengthsException extends Exception {
    private final List<Integer> vectorLengths;

    public DifferentVectorsLengthsException(List<Integer> vectorLengths, String message) {
        super(message);
        this.vectorLengths = vectorLengths;
    }

    public List<Integer> getVectorLengths() {
        return vectorLengths;
    }
}
