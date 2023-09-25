package c.inner;

public class AnonymousSample {
    public static void main(String args[]) {
        AnonymousSample sample = new AnonymousSample();
        sample.setButtonListener();
    }

    public void setButtonListener() {
        MagicButton button = new MagicButton();
        button.setListener(new EventListener() {
            public void onClick() {
                System.out.println("anonymous");
            }
        });
        button.onClickProcess();
    }

}