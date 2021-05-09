package app;

import clinica.Clinica;


public class Prueba {

    public static void main(String[] args) {
        // Creamos la Clinica
        Clinica clinica = Clinica.getInstance("Hospital", "Avenida siempre viva", "123123", "Balcarce");
        // Creacion de medicos
        clinica.addMedico("234565", "Marconi 2345", "Mar del Plata", "223456732", "Debora Melo", 1406, 3000, "Clinico",
                "Permanente");
        clinica.addMedico("82216", "Moreno 4562", "Mar del Plata", "6872312", "Monica Galindo", 1407, 5000, "Cirujano",
                "Temporario");
        clinica.addMedico("7432138", "Belgrano 2135", "Mar del Plata", "5761234", "Elba Calao", 1408, 6000, "Pediatra",
                "Permanente");
        clinica.addMedico("867321", "Colon 1235", "Mar del Plata", "57658432", "Benito Camelo", 1409, 7000, "Clinico",
                "Temporario");
        // Creamos un par de pacientes
        clinica.ingresaPaciente("65761321", "Moreno 1239", "Mar del plata", "2234564687", "Verconica Galindo", "Nino");
        clinica.ingresaPaciente("7561238", "Colon 1239", "Mar del plata", "6873213", "Elmer Melada", "joven");
        clinica.ingresaPaciente("1354314", "Buenos Aires 1239", "Mar del plata", "2234564687", "Elmer Idiano de Greenwich", "Nino");
        // Ingreso del paciente a la clínica

    }
}
