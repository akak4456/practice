package d.generic;

import java.io.Serializable;

public class CastingGenericDTO<B> implements Serializable {
    private B object;
    public void setObject(B obj) {
        this.object = obj;
    }
    public B getObject() {
        return object;
    }
}
