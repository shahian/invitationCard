public class PersianCharachtersUnicode {

    char c;
    private String InitialFom_Unicode;
    private String MedialForm_Unicode;
    private String FinalForm_Unicode;
    private String IsolatedForm_Unicode;

    public void setCharc(char c) {
        this.c = c;
        calculate();
    }

    private void calculate() {
        switch (c) {


            case '?':
                InitialFom_Unicode = "\uFE91";
                MedialForm_Unicode = "\uFE92";
                FinalForm_Unicode = "\uFE90";
                IsolatedForm_Unicode = "\uFE8F";
                break;


            default:
                break;
        }
    }

    public String getInitialFom_Unicode() {
        return InitialFom_Unicode;
    }

    public String getFinalForm_Unicode() {
        return FinalForm_Unicode;
    }

    public String getIsolatedForm_Unicode() {
        return IsolatedForm_Unicode;
    }

    public String getMedialForm_Unicode() {
        return MedialForm_Unicode;
    }
}