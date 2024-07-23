package com.amostras.api_amostras.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "amostras")
public class AmostrasDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cidade")
    private String cidade;

    @Embedded
    private DadosMercado dados;

    public AmostrasDto() {
    }

    public AmostrasDto(String cidade, DadosMercado dados) {
        this.cidade = cidade;
        this.dados = dados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public DadosMercado getDados() {
        return dados;
    }

    public void setDados(DadosMercado dados) {
        this.dados = dados;
    }

    @Embeddable
    public static class DadosMercado {
        @Column(name = "endereco")
        private String endereco;

        @Column(name = "bairro")
        private String bairro;

        @Column(name = "area")
        private double area;

        @Column(name = "preco")
        private double preco;

        @Column(name = "quartos")
        private int quartos;
        
        @Column(name = "banheiros")
        private int ban;
        
        @Column(name = "vagas")
        private int vaga;

        @Column(name = "URL")
        private String url;


        public DadosMercado() {
        }

        public DadosMercado(String endereco, String bairro, double area, double preco, int quartos, int ban, int vaga, String url) {
            this.endereco = endereco;
            this.bairro = bairro;
            this.area = area;
            this.preco = preco;
            this.quartos = quartos;
            this.ban = ban;
            this.vaga = vaga;
            this.url = url;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }
        public int getQuartos() {
            return quartos;
        }

        public void setQuartos(int quartos) {
            this.quartos = quartos;
        }

        public int getBan() {
            return ban;
        }

        public void setBan(int ban) {
            this.ban = ban;
        }

        public int getVaga() {
            return vaga;
        }

        public void setVaga(int vaga) {
            this.vaga = vaga;
        }
        
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
