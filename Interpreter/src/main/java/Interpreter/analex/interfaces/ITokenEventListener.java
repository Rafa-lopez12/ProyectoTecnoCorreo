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
    
    void error(TokenEvent tokenEvent);
}
