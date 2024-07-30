package proyecto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *Clase que sobreescribe ObjectOutputStream
 * @author Mihai Gabriel Laudoiu
 */
public class ObjectOutputStreamLMG extends ObjectOutputStream {

    private boolean append;

    public ObjectOutputStreamLMG(OutputStream out, boolean append) throws IOException {
        super(out);
        this.append = append;
    }

    public ObjectOutputStreamLMG(FileOutputStream fileOutputStream) throws IOException {
        super();
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        if (!append) {
            super.writeStreamHeader();
        } else {
            reset();
        }
    }
}
