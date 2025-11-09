package utils;

import java.util.ArrayList;

public class HtmlTableGenerator {
    
    public static String generateAlumnoTable(ArrayList<String[]> alumnos) {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h2 { color: #333; }");
        html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append("th { background-color: #4CAF50; color: white; padding: 12px; text-align: left; }");
        html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
        html.append("tr:hover { background-color: #f5f5f5; }");
        html.append(".total { margin-top: 20px; font-weight: bold; color: #4CAF50; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        
        html.append("<h2>📋 Lista de Alumnos</h2>");
        
        if (alumnos.isEmpty()) {
            html.append("<p>No hay alumnos registrados.</p>");
        } else {
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>ID</th>");
            html.append("<th>Nombre</th>");
            html.append("<th>Apellido</th>");
            html.append("<th>Email</th>");
            html.append("<th>Teléfono</th>");
            html.append("<th>Fecha Nacimiento</th>");
            html.append("<th>Dirección</th>");
            html.append("<th>Estado</th>");
            html.append("<th>Grado</th>");
            html.append("<th>Fecha Ingreso</th>");
            html.append("</tr>");
            
            for (String[] alumno : alumnos) {
                html.append("<tr>");
                for (String campo : alumno) {
                    html.append("<td>").append(campo != null ? campo : "").append("</td>");
                }
                html.append("</tr>");
            }
            
            html.append("</table>");
            html.append("<p class='total'>Total de alumnos: ").append(alumnos.size()).append("</p>");
        }
        
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
    
    public static String generateUsuarioTable(ArrayList<String[]> usuarios) {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h2 { color: #333; }");
        html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append("th { background-color: #2196F3; color: white; padding: 12px; text-align: left; }");
        html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
        html.append("tr:hover { background-color: #f5f5f5; }");
        html.append(".total { margin-top: 20px; font-weight: bold; color: #2196F3; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        
        html.append("<h2>👥 Lista de Usuarios</h2>");
        
        if (usuarios.isEmpty()) {
            html.append("<p>No hay usuarios registrados.</p>");
        } else {
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>ID</th>");
            html.append("<th>Nombre</th>");
            html.append("<th>Apellido</th>");
            html.append("<th>Email</th>");
            html.append("<th>Teléfono</th>");
            html.append("<th>Fecha Nacimiento</th>");
            html.append("<th>Dirección</th>");
            html.append("<th>Estado</th>");
            html.append("</tr>");
            
            for (String[] usuario : usuarios) {
                html.append("<tr>");
                for (String campo : usuario) {
                    html.append("<td>").append(campo != null ? campo : "").append("</td>");
                }
                html.append("</tr>");
            }
            
            html.append("</table>");
            html.append("<p class='total'>Total de usuarios: ").append(usuarios.size()).append("</p>");
        }
        
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
    
    public static String generateTutorTable(ArrayList<String[]> tutores) {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h2 { color: #333; }");
        html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append("th { background-color: #FF9800; color: white; padding: 12px; text-align: left; }");
        html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
        html.append("tr:hover { background-color: #f5f5f5; }");
        html.append(".total { margin-top: 20px; font-weight: bold; color: #FF9800; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        
        html.append("<h2>👨‍🏫 Lista de Tutores</h2>");
        
        if (tutores.isEmpty()) {
            html.append("<p>No hay tutores registrados.</p>");
        } else {
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>ID</th>");
            html.append("<th>Nombre</th>");
            html.append("<th>Apellido</th>");
            html.append("<th>Email</th>");
            html.append("<th>Teléfono</th>");
            html.append("<th>Fecha Nacimiento</th>");
            html.append("<th>Dirección</th>");
            html.append("<th>Estado</th>");
            html.append("</tr>");
            
            for (String[] tutor : tutores) {
                html.append("<tr>");
                for (String campo : tutor) {
                    html.append("<td>").append(campo != null ? campo : "").append("</td>");
                }
                html.append("</tr>");
            }
            
            html.append("</table>");
            html.append("<p class='total'>Total de tutores: ").append(tutores.size()).append("</p>");
        }
        
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
    
    
    
    public static String generateHorarioTable(ArrayList<String[]> horarios) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h2 { color: #333; }");
        html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append("th { background-color: #9C27B0; color: white; padding: 12px; text-align: left; }");
        html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
        html.append("tr:hover { background-color: #f5f5f5; }");
        html.append(".total { margin-top: 20px; font-weight: bold; color: #9C27B0; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<h2>🕐 Lista de Horarios</h2>");

        if (horarios.isEmpty()) {
            html.append("<p>No hay horarios registrados.</p>");
        } else {
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>ID</th>");
            html.append("<th>Día</th>");
            html.append("<th>Hora Inicio</th>");
            html.append("<th>Hora Fin</th>");
            html.append("<th>Estado</th>");
            html.append("</tr>");

            for (String[] horario : horarios) {
                html.append("<tr>");
                for (String campo : horario) {
                    html.append("<td>").append(campo != null ? campo : "").append("</td>");
                }
                html.append("</tr>");
            }

            html.append("</table>");
            html.append("<p class='total'>Total de horarios: ").append(horarios.size()).append("</p>");
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    public static String generateTutorHorarioTable(ArrayList<String[]> asignaciones) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h2 { color: #333; }");
        html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append("th { background-color: #E91E63; color: white; padding: 12px; text-align: left; }");
        html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
        html.append("tr:hover { background-color: #f5f5f5; }");
        html.append(".total { margin-top: 20px; font-weight: bold; color: #E91E63; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<h2>📅 Asignaciones Tutor-Horario</h2>");

        if (asignaciones.isEmpty()) {
            html.append("<p>No hay asignaciones registradas.</p>");
        } else {
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>ID</th>");
            html.append("<th>Tutor</th>");
            html.append("<th>Día</th>");
            html.append("<th>Hora Inicio</th>");
            html.append("<th>Hora Fin</th>");
            html.append("<th>Fecha Asignación</th>");
            html.append("</tr>");

            for (String[] asignacion : asignaciones) {
                html.append("<tr>");
                for (String campo : asignacion) {
                    html.append("<td>").append(campo != null ? campo : "").append("</td>");
                }
                html.append("</tr>");
            }

            html.append("</table>");
            html.append("<p class='total'>Total de asignaciones: ").append(asignaciones.size()).append("</p>");
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
    
    
    public static String generateInscripcionTable(ArrayList<String[]> inscripciones) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h2 { color: #333; }");
        html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append("th { background-color: #00BCD4; color: white; padding: 12px; text-align: left; }");
        html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
        html.append("tr:hover { background-color: #f5f5f5; }");
        html.append(".activo { color: #4CAF50; font-weight: bold; }");
        html.append(".retirado { color: #F44336; font-weight: bold; }");
        html.append(".finalizado { color: #2196F3; font-weight: bold; }");
        html.append(".total { margin-top: 20px; font-weight: bold; color: #00BCD4; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<h2>📝 Lista de Inscripciones</h2>");

        if (inscripciones.isEmpty()) {
            html.append("<p>No hay inscripciones registradas.</p>");
        } else {
            html.append("<table>");
            html.append("<tr>");
            html.append("<th>ID</th>");
            html.append("<th>Alumno</th>");
            html.append("<th>Tutor</th>");
            html.append("<th>Fecha Inscripción</th>");
            html.append("<th>Observaciones</th>");
            html.append("</tr>");

            for (String[] inscripcion : inscripciones) {
                html.append("<tr>");
                // inscripcion[0] = id
                html.append("<td>").append(inscripcion[0]).append("</td>");
                // inscripcion[3] = alumno nombre completo
                html.append("<td><strong>").append(inscripcion[3]).append("</strong></td>");
                // inscripcion[4] = tutor nombre completo
                html.append("<td>").append(inscripcion[4]).append("</td>");
                // inscripcion[5] = fecha_inscripcion
                html.append("<td>").append(inscripcion[5]).append("</td>");
                // inscripcion[6] = estado
                String estado = inscripcion[6];
                String cssClass = "";
                if (estado.equals("activo")) cssClass = "activo";
                else if (estado.equals("retirado")) cssClass = "retirado";
                else if (estado.equals("finalizado")) cssClass = "finalizado";
                html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");
                // inscripcion[7] = observaciones
                html.append("<td>").append(inscripcion[7]).append("</td>");
                html.append("</tr>");
            }

            html.append("</table>");
            html.append("<p class='total'>Total de inscripciones: ").append(inscripciones.size()).append("</p>");
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}