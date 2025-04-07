public class Data {
    private String Municipio;
    private String Tipologia;
    private String Classificazione;
    private String Denominazione;
    private String Indirizzo;

    private String Singole;
    private String Doppie;
    private String Triple;
    private String Quadruple;
    private String Quintuple;
    private String Sestuple;
    private String TotaleCamere;
    private String PostiLetto;
    private String UnitaAbitative;
    private String PostiLettoUnitaAbitative;

    public Data(String municipio, String tipologia, String classificazione, String denominazione, String indirizzo,
            String singole, String doppie, String triple, String quadruple, String quintuple, String sestuple,
            String totaleCamere, String postiLetto, String unitaAbitative, String postiLettoUnitaAbitative) {
        Municipio = municipio;
        Tipologia = tipologia;
        Classificazione = classificazione;
        Denominazione = denominazione;
        Indirizzo = indirizzo;
        Singole = singole;
        Doppie = doppie;
        Triple = triple;
        Quadruple = quadruple;
        Quintuple = quintuple;
        Sestuple = sestuple;
        TotaleCamere = totaleCamere;
        PostiLetto = postiLetto;
        UnitaAbitative = unitaAbitative;
        PostiLettoUnitaAbitative = postiLettoUnitaAbitative;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    public String getTipologia() {
        return Tipologia;
    }

    public void setTipologia(String tipologia) {
        Tipologia = tipologia;
    }

    public String getClassificazione() {
        return Classificazione;
    }

    public void setClassificazione(String classificazione) {
        Classificazione = classificazione;
    }

    public String getDenominazione() {
        return Denominazione;
    }

    public void setDenominazione(String denominazione) {
        Denominazione = denominazione;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }

    public String getSingole() {
        return Singole;
    }

    public void setSingole(String singole) {
        Singole = singole;
    }

    public String getDoppie() {
        return Doppie;
    }

    public void setDoppie(String doppie) {
        Doppie = doppie;
    }

    public String getTriple() {
        return Triple;
    }

    public void setTriple(String triple) {
        Triple = triple;
    }

    public String getQuadruple() {
        return Quadruple;
    }

    public void setQuadruple(String quadruple) {
        Quadruple = quadruple;
    }

    public String getQuintuple() {
        return Quintuple;
    }

    public void setQuintuple(String quintuple) {
        Quintuple = quintuple;
    }

    public String getSestuple() {
        return Sestuple;
    }

    public void setSestuple(String sestuple) {
        Sestuple = sestuple;
    }

    public String getTotaleCamere() {
        return TotaleCamere;
    }

    public void setTotaleCamere(String totaleCamere) {
        TotaleCamere = totaleCamere;
    }

    public String getPostiLetto() {
        return PostiLetto;
    }

    public void setPostiLetto(String postiLetto) {
        PostiLetto = postiLetto;
    }

    public String getUnitaAbitative() {
        return UnitaAbitative;
    }

    public void setUnitaAbitative(String unitaAbitative) {
        UnitaAbitative = unitaAbitative;
    }

    public String getPostiLettoUnitaAbitative() {
        return PostiLettoUnitaAbitative;
    }

    public void setPostiLettoUnitaAbitative(String postiLettoUnitaAbitative) {
        PostiLettoUnitaAbitative = postiLettoUnitaAbitative;
    }

    @Override
    public String toString() {
        return "\nMunicipio: " + Municipio + ", Tipologia: " + Tipologia + ", Classificazione: " + Classificazione
                + ", Denominazione: " + Denominazione + ", Indirizzo: " + Indirizzo + ", Singole: " + Singole
                + ", Doppie:"+ Doppie + ", Triple: " + Triple + ", Quadruple: " + Quadruple + ", Quintuple: " + Quintuple
                + ", Sestuple: " + Sestuple + ", TotaleCamere: " + TotaleCamere + ", PostiLetto: " + PostiLetto
                + ", UnitaAbitative: " + UnitaAbitative + ", PostiLettoUnitaAbitative: " + PostiLettoUnitaAbitative;
    }

}
