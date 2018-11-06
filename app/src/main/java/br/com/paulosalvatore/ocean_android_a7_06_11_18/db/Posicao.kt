package br.com.paulosalvatore.ocean_a7_06_11_18.db

data class Posicao(var id: Int,
                   var latitude: Double,
                   var longitude: Double,
                   var dataHora: String) {
    constructor(latitude: Double,
                longitude: Double,
                dataHora: String) : this(0, latitude, longitude, dataHora)
}
