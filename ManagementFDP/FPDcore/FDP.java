package ManagementFDP.FPDcore;

public class FDP {
    /*
     * Se define por el autor, título, tutor/tutores, estado (INSCRITO/FINALIZADO) y calificación.
     * Podrá tener uno o dos tutores distintos.
     * 
     * Dos tipos: experimentales o teóricos.
     * 
     * Al crear el FDP tendrá un solo tutor, estado INSCRITO y se incorporará a la lista inscritos.
     * 
     * Método finalizar() al terminar el FPD se le pondrá una calificación y se cambiará a la lista finalizados.
     * Además imprimirá el autor, título, tutor/tutores y la url o memoria.
     * 
     * Método cambio() para cambiar el tipo de teórica a experimental y viceversa. Devolviendo el FDP transformado.
     * Y una cadena vacía a los atributos extras.
     * 
     * Método segundoTutor() para añadir un segundo tutor comprobando que no tiene dos tutores y
     * no se puede incluir de nuevo al segundo tutor. Si no lanzará la excepción "ESegundoTutorRepetidoException".
     */
}