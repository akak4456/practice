package c.annotation;

public class AnnotationSample {
    public void useDeprecated() {
        AnnotationDeprecated child = new AnnotationDeprecated();
        child.noMoreUse();
    }
}