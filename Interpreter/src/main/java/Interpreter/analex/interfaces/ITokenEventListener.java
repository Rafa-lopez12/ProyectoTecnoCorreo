/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interpreter.analex.interfaces;

import Interpreter.events.TokenEvent;

/**
 *
 * @author Rafa
 */
public interface ITokenEventListener {
    void usuario(TokenEvent tokenEvent);
    void alumno(TokenEvent tokenEvent);
    void tutor(TokenEvent tokenEvent);
    void horario(TokenEvent tokenEvent);
    void tutor_horario(TokenEvent tokenEvent);
    void inscripcion(TokenEvent tokenEvent);
    void servicio(TokenEvent tokenEvent);
    void informeclase(TokenEvent tokenEvent);
    void error(TokenEvent tokenEvent);
    void asistencia(TokenEvent tokenEvent);
    void licencia(TokenEvent tokenEvent);
    void reprogramacion(TokenEvent tokenEvent);
    void venta(TokenEvent tokenEvent);
    void pago(TokenEvent tokenEvent);
    void reporte(TokenEvent tokenEvent);
}
