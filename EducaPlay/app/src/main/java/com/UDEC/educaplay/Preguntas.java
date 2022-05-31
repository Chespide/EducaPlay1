package com.UDEC.educaplay;

public class Preguntas {
    private  String tex;
    private String tex1;
    private String tex2;
    private String tex3;
    private String tex4;
    private String tex5;

    public Preguntas(String tex, String tex1, String tex2, String tex3, String tex4, String tex5) {
        this.tex = tex;
        this.tex1 = tex1;
        this.tex2 = tex2;
        this.tex3 = tex3;
        this.tex4 = tex4;
        this.tex5 = tex5;
    }

    public String getTex() {
        return tex;
    }

    public void setTex(String tex) {
        this.tex = tex;
    }

    public String getTex1() {
        return tex1;
    }

    public void setTex1(String tex1) {
        this.tex1 = tex1;
    }

    public String getTex2() {
        return tex2;
    }

    public void setTex2(String tex2) {
        this.tex2 = tex2;
    }

    public String getTex3() {
        return tex3;
    }

    public void setTex3(String tex3) {
        this.tex3 = tex3;
    }

    public String getTex4() {
        return tex4;
    }

    public void setTex4(String tex4) {
        this.tex4 = tex4;
    }

    public String getTex5() {
        return tex5;
    }

    public void setTex5(String tex5) {
        this.tex5 = tex5;
    }
}
