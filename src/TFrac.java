public class TFrac implements Comparable<TFrac> {
    private long nominator;
    private long denominator;

    public TFrac(long n, long d) {
        if(d == 0)
            throw new ZeroDenominatorException();
        long nd = nod(n, d);
        nominator = n / nd;
        denominator = d / nd;
    }

    public TFrac(String number) {
        String[] data = number.split("/");
        nominator = Long.parseLong(data[0]);
        denominator = Long.parseLong(data[1]);
        if(denominator == 0)
            throw new ZeroDenominatorException();
        minimisation();
    }

    public TFrac copy(TFrac other) {
        return new TFrac(nominator, denominator);
    }

    private long nod(long f, long s){
        f = Math.abs(f);
        s = Math.abs(s);
        while (f != 0 && s != 0){
            if(f > s){
                f %= s;
            } else {
                s %= f;
            }
        }
        return f + s;
    }

    private void toOneDenominator(TFrac other) {
        if (other.nominator != 0) {
            long resultDenominator = denominator / nod(denominator, other.denominator) * other.denominator;
            nominator *= resultDenominator / denominator;
            other.nominator *= resultDenominator / other.denominator;
            denominator = resultDenominator;
            other.denominator = resultDenominator;
        }
    }

    private void minimisation(){
        long nd = nod(nominator, denominator);
        nominator /= nd;
        denominator /= nd;
    }

    public TFrac inversion() {
        return new TFrac(-nominator, denominator);
    }

    public TFrac converse(){
        return new TFrac(denominator, nominator);
    }

    public TFrac multi(TFrac other) {
        TFrac now = copy(this);
        now.nominator *= other.nominator;
        now.denominator *= other.denominator;
        if (now.denominator < 0) {
            now.denominator *= -1;
            now = now.inversion();
        }
        now.minimisation();
        return now;
    }

    public TFrac square(){
        return new TFrac((long)Math.pow(nominator, 2), (long)Math.pow(denominator, 2));
    }

    public TFrac div(TFrac other) {
        TFrac now = copy(this);
        now.nominator *= other.denominator;
        now.denominator *= other.nominator;
        if (now.denominator < 0) {
            now.denominator *= -1;
            now = now.inversion();
        }
        now.minimisation();
        return now;
    }

    public TFrac plus(TFrac other) {
        TFrac oth = copy(this);
        TFrac now = copy(this);
        now.toOneDenominator(oth);
        now.nominator += oth.nominator;
        now.minimisation();
        return now;
    }

    public TFrac minus(TFrac other) {
        TFrac oth = copy(this);
        TFrac now = copy(this);
        now.toOneDenominator(oth);
        now.nominator -= oth.nominator;
        now.minimisation();
        return now;
    }

    public boolean equally(TFrac other){
        return compareTo(other) == 0;
    }

    public boolean more(TFrac other){
        return compareTo(other) > 0;
    }

    public long getDenominator() {
        return denominator;
    }

    public long getNominator() {
        return nominator;
    }

    public String getNominatorInString(){
        return Long.toString(nominator);
    }

    public String getDenominatorInString(){
        return Long.toString(denominator);
    }

    @Override
    public String toString() {
        long nom = nominator;
        long den = denominator;
        long nd = nod(nom, den);
        nom /= nd;
        den /= nd;
        return (den == 1 || nom == 0 ? Long.toString(nom) : nom + "/" + den);
    }

    @Override
    public int compareTo(TFrac o) {
        toOneDenominator(o);
        return Long.compare(nominator, o.nominator);
    }
}
