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
            html.append("<th>Código</th>");
            html.append("<th>Estado</th>");
            html.append("<th>Grado</th>");
            html.append("<th>Fecha Ingreso</th>");
            html.append("</tr>");
            
            for (String[] alumno : alumnos) {
                html.append("<tr>");
                for (String campo : alumno) {
                    html.append("<td><strong>").append(alumno[8]).append("</strong></td>");
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
           html.append("th { background-color: #00BCD4; color: white; padding: 12px; text-align: left; font-size: 12px; }");
           html.append("td { padding: 10px; border-bottom: 1px solid #ddd; font-size: 12px; }");
           html.append("tr:hover { background-color: #f5f5f5; }");
           html.append(".activo { color: #4CAF50; font-weight: bold; }");
           html.append(".retirado { color: #F44336; font-weight: bold; }");
           html.append(".finalizado { color: #2196F3; font-weight: bold; }");
           html.append(".total { margin-top: 20px; font-weight: bold; color: #00BCD4; }");
           html.append(".truncate { max-width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }");
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
               html.append("<th>Modalidad</th>");
               html.append("<th>Alumno</th>");
               html.append("<th>Tutor</th>");
               html.append("<th>Fecha</th>");
               html.append("<th>Horarios</th>");
               html.append("<th>Estado</th>");
               html.append("<th>Observaciones</th>");
               html.append("</tr>");

               for (String[] inscripcion : inscripciones) {
                   // inscripcion[0] = id
                   // inscripcion[1] = servicio_id
                   // inscripcion[2] = alumno_id
                   // inscripcion[3] = tutor_id
                   // inscripcion[4] = servicio_nombre
                   // inscripcion[5] = alumno_nombre
                   // inscripcion[6] = tutor_nombre
                   // inscripcion[7] = fecha_inscripcion
                   // inscripcion[8] = direccion
                   // inscripcion[9] = foto_url
                   // inscripcion[10] = estado
                   // inscripcion[11] = observaciones

                   html.append("<tr>");
                   html.append("<td>").append(inscripcion[0]).append("</td>");
                   html.append("<td><strong>").append(inscripcion[4]).append("</strong></td>");
                   html.append("<td>").append(inscripcion[5]).append("</td>");
                   html.append("<td>").append(inscripcion[6]).append("</td>");
                   html.append("<td>").append(inscripcion[7]).append("</td>");

                    String horariosJson = inscripcion[8] != null ? inscripcion[8] : "N/A";
                    String horariosDisplay = horariosJson.replace("[", "").replace("]", "")
                                             .replace("{", "").replace("}", "")
                                             .replace("\"dia\":", "").replace("\"hora_inicio\":", "")
                                             .replace("\"hora_fin\":", "").replace("\"", "")
                                             .replace(",", " - ");
                    html.append("<td>").append(horariosDisplay).append("</td>");

                   // Estado
                   String estado = inscripcion[10];
                   String cssClass = "";
                   if (estado.equals("activo")) cssClass = "activo";
                   else if (estado.equals("retirado")) cssClass = "retirado";
                   else if (estado.equals("finalizado")) cssClass = "finalizado";
                   html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                   // Observaciones
                   String obs = inscripcion[11] != null ? inscripcion[11] : "";
                   html.append("<td class='truncate' title='").append(obs).append("'>").append(obs).append("</td>");
                   html.append("</tr>");
               }

               html.append("</table>");
               html.append("<p class='total'>Total de inscripciones: ").append(inscripciones.size()).append("</p>");
           }

           html.append("</body>");
           html.append("</html>");

           return html.toString();
       }
        
        
       public static String generateInscripcionesPorAlumnoTable(ArrayList<String[]> inscripciones, String nombreAlumno) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".subtitle { color: #666; font-size: 16px; margin-bottom: 20px; }");
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

            html.append("<h2>📚 Inscripciones del Alumno</h2>");
            html.append("<p class='subtitle'>Alumno: <strong>").append(nombreAlumno).append("</strong></p>");

            if (inscripciones.isEmpty()) {
                html.append("<p>Este alumno no tiene inscripciones registradas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Modalidad</th>");
                html.append("<th>Tutor</th>");
                html.append("<th>Fecha Inscripción</th>");
                html.append("<th>Estado</th>");
                html.append("<th>Observaciones</th>");
                html.append("</tr>");

                // inscripcion[0] = id
                // inscripcion[1] = servicio_id
                // inscripcion[2] = servicio_nombre
                // inscripcion[3] = tutor_nombre
                // inscripcion[4] = fecha_inscripcion
                // inscripcion[5] = estado
                // inscripcion[6] = observaciones

                for (String[] inscripcion : inscripciones) {
                    html.append("<tr>");
                    html.append("<td>").append(inscripcion[0]).append("</td>");
                    html.append("<td><strong>").append(inscripcion[2]).append("</strong></td>");
                    html.append("<td>").append(inscripcion[3]).append("</td>");
                    html.append("<td>").append(inscripcion[4]).append("</td>");

                    String estado = inscripcion[5];
                    String cssClass = "";
                    if (estado.equals("activo")) cssClass = "activo";
                    else if (estado.equals("retirado")) cssClass = "retirado";
                    else if (estado.equals("finalizado")) cssClass = "finalizado";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("<td>").append(inscripcion[6] != null ? inscripcion[6] : "").append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de inscripciones: ").append(inscripciones.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        // Tabla de inscripciones por tutor
        public static String generateInscripcionesPorTutorTable(ArrayList<String[]> inscripciones, String nombreTutor) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".subtitle { color: #666; font-size: 16px; margin-bottom: 20px; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #FF9800; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".activo { color: #4CAF50; font-weight: bold; }");
            html.append(".retirado { color: #F44336; font-weight: bold; }");
            html.append(".finalizado { color: #2196F3; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #FF9800; }");
            html.append(".truncate { max-width: 150px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>👨‍🏫 Alumnos Asignados</h2>");
            html.append("<p class='subtitle'>Tutor: <strong>").append(nombreTutor).append("</strong></p>");

            if (inscripciones.isEmpty()) {
                html.append("<p>Este tutor no tiene alumnos asignados.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Código</th>");
                html.append("<th>Grado</th>");
                html.append("<th>Modalidad</th>");
                html.append("<th>Fecha Inscripción</th>");
                html.append("<th>Dirección</th>");
                html.append("<th>Estado</th>");
                html.append("<th>Observaciones</th>");
                html.append("</tr>");

                // inscripcion[0] = id
                // inscripcion[1] = alumno_id
                // inscripcion[2] = servicio_id
                // inscripcion[3] = servicio_nombre
                // inscripcion[4] = alumno_nombre
                // inscripcion[5] = grado_escolar
                // inscripcion[6] = fecha_inscripcion
                // inscripcion[7] = direccion
                // inscripcion[8] = estado
                // inscripcion[9] = observaciones

                for (String[] inscripcion : inscripciones) {
                    html.append("<tr>");
                    html.append("<td>").append(inscripcion[0]).append("</td>");
                    html.append("<td><strong>").append(inscripcion[4]).append("</strong></td>");
                    html.append("<td>").append(inscripcion[5]).append("</td>");
                    html.append("<td>").append(inscripcion[3]).append("</td>");
                    html.append("<td>").append(inscripcion[6]).append("</td>");

                    String direccion = inscripcion[7] != null ? inscripcion[7] : "N/A";
                    html.append("<td class='truncate' title='").append(direccion).append("'>").append(direccion).append("</td>");

                    String estado = inscripcion[8];
                    String cssClass = "";
                    if (estado.equals("activo")) cssClass = "activo";
                    else if (estado.equals("retirado")) cssClass = "retirado";
                    else if (estado.equals("finalizado")) cssClass = "finalizado";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("<td class='truncate'>").append(inscripcion[9] != null ? inscripcion[9] : "").append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de alumnos: ").append(inscripciones.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        // Tabla simple de inscripciones por servicio (ya existe otra versión en DServicio)
        public static String generateInscripcionesPorServicioTable(ArrayList<String[]> inscripciones, String nombreServicio) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".subtitle { color: #666; font-size: 16px; margin-bottom: 20px; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #673AB7; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".activo { color: #4CAF50; font-weight: bold; }");
            html.append(".retirado { color: #F44336; font-weight: bold; }");
            html.append(".finalizado { color: #2196F3; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #673AB7; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>📝 Inscripciones por Modalidad</h2>");
            html.append("<p class='subtitle'>Modalidad: <strong>").append(nombreServicio).append("</strong></p>");

            if (inscripciones.isEmpty()) {
                html.append("<p>No hay inscripciones para esta modalidad.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Tutor</th>");
                html.append("<th>Fecha Inscripción</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                // inscripcion[0] = id
                // inscripcion[1] = alumno_nombre
                // inscripcion[2] = tutor_nombre
                // inscripcion[3] = fecha_inscripcion
                // inscripcion[4] = estado

                for (String[] inscripcion : inscripciones) {
                    html.append("<tr>");
                    html.append("<td>").append(inscripcion[0]).append("</td>");
                    html.append("<td><strong>").append(inscripcion[1]).append("</strong></td>");
                    html.append("<td>").append(inscripcion[2]).append("</td>");
                    html.append("<td>").append(inscripcion[3]).append("</td>");

                    String estado = inscripcion[4];
                    String cssClass = "";
                    if (estado.equals("activo")) cssClass = "activo";
                    else if (estado.equals("retirado")) cssClass = "retirado";
                    else if (estado.equals("finalizado")) cssClass = "finalizado";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de inscripciones: ").append(inscripciones.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }
    
    
            // Tabla de servicios
        public static String generateServicioTable(ArrayList<String[]> servicios) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #673AB7; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".activo { color: #4CAF50; font-weight: bold; }");
            html.append(".inactivo { color: #F44336; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #673AB7; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>📚 Lista de Servicios</h2>");

            if (servicios.isEmpty()) {
                html.append("<p>No hay servicios registrados.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Nombre</th>");
                html.append("<th>Descripción</th>");
                html.append("<th>Req. Dirección</th>");
                html.append("<th>Req. Foto</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] servicio : servicios) {
                    html.append("<tr>");
                    html.append("<td>").append(servicio[0]).append("</td>");
                    html.append("<td><strong>").append(servicio[1]).append("</strong></td>");
                    html.append("<td>").append(servicio[2] != null ? servicio[2] : "").append("</td>");
                    html.append("<td>").append(servicio[3].equals("true") ? "✓ Sí" : "✗ No").append("</td>");
                    html.append("<td>").append(servicio[4].equals("true") ? "✓ Sí" : "✗ No").append("</td>");

                    String estado = servicio[5].equals("true") ? "ACTIVO" : "INACTIVO";
                    String cssClass = servicio[5].equals("true") ? "activo" : "inactivo";
                    html.append("<td class='").append(cssClass).append("'>").append(estado).append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de servicios: ").append(servicios.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }
        
        
        public static String generateInformeClaseTable(ArrayList<String[]> informes) {
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>");
            html.append("<html><head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; margin: 20px; }");
            html.append("h2 { color: #333; }");
            html.append("table { border-collapse: collapse; width: 100%; margin-top: 20px; }");
            html.append("th { background-color: #4CAF50; color: white; padding: 12px; text-align: left; font-weight: bold; }");
            html.append("td { border: 1px solid #ddd; padding: 10px; }");
            html.append("tr:nth-child(even) { background-color: #f2f2f2; }");
            html.append("tr:hover { background-color: #e0e0e0; }");
            html.append(".excelente { color: #4CAF50; font-weight: bold; }");
            html.append(".bueno { color: #2196F3; }");
            html.append(".regular { color: #FF9800; }");
            html.append(".necesita_refuerzo { color: #F44336; }");
            html.append(".calificacion { text-align: center; font-weight: bold; font-size: 1.1em; }");
            html.append("</style>");
            html.append("</head><body>");
            html.append("<h2>📋 Lista de Informes de Clase</h2>");

            if (informes.isEmpty()) {
                html.append("<p>No hay informes registrados.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Asistencia ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Tutor</th>");
                html.append("<th>Servicio</th>");
                html.append("<th>Fecha</th>");
                html.append("<th>Temas Vistos</th>");
                html.append("<th>Comprensión</th>");
                html.append("<th>Participación</th>");
                html.append("<th>Calificación</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] informe : informes) {
                    html.append("<tr>");
                    html.append("<td>").append(informe[0]).append("</td>"); // ID
                    html.append("<td>").append(informe[1]).append("</td>");
                    html.append("<td>").append(informe[2]).append("</td>"); // Alumno
                    html.append("<td>").append(informe[3]).append("</td>"); // Tutor
                    html.append("<td>").append(informe[4]).append("</td>"); // Servicio
                    html.append("<td>").append(informe[5]).append("</td>"); // Fecha

                    // Temas vistos (truncar si es muy largo)
                    String temas = informe[6] != null ? informe[6] : "";
                    if (temas.length() > 50) {
                        temas = temas.substring(0, 47) + "...";
                    }
                    html.append("<td>").append(temas).append("</td>");

                    // Nivel de comprensión con color
                    String comprension = informe[7] != null ? informe[7] : "N/A";
                    String claseComprension = "";
                    switch(comprension.toLowerCase()) {
                        case "excelente":
                            claseComprension = "excelente";
                            break;
                        case "bueno":
                            claseComprension = "bueno";
                            break;
                        case "regular":
                            claseComprension = "regular";
                            break;
                        case "necesita_refuerzo":
                            claseComprension = "necesita_refuerzo";
                            break;
                    }
                    html.append("<td class='").append(claseComprension).append("'>").append(comprension).append("</td>");

                    // Participación
                    html.append("<td>").append(informe[8] != null ? informe[8] : "N/A").append("</td>");

                    // Calificación
                    html.append("<td class='calificacion'>").append(informe[9]).append("</td>");

                    // Estado
                    html.append("<td>").append(informe[10]).append("</td>");

                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p style='margin-top: 20px; color: #666;'>Total de informes: ").append(informes.size()).append("</p>");
            }

            html.append("</body></html>");
            return html.toString();
        }
        
        
        public static String generateAsistenciaTable(ArrayList<String[]> asistencias) {
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
            html.append(".presente { color: #4CAF50; font-weight: bold; }");
            html.append(".ausente { color: #F44336; font-weight: bold; }");
            html.append(".tardanza { color: #FF9800; font-weight: bold; }");
            html.append(".justificado { color: #2196F3; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #4CAF50; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>📋 Lista de Asistencias</h2>");

            if (asistencias.isEmpty()) {
                html.append("<p>No hay asistencias registradas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Inscripción ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Tutor</th>");
                html.append("<th>Servicio</th>");
                html.append("<th>Fecha</th>");
                html.append("<th>Estado</th>");
                html.append("<th>Observaciones</th>");
                html.append("</tr>");

                // asistencia[0] = id
                // asistencia[1] = inscripcion_id
                // asistencia[2] = alumno_nombre
                // asistencia[3] = tutor_nombre
                // asistencia[4] = servicio_nombre
                // asistencia[5] = fecha
                // asistencia[6] = estado
                // asistencia[7] = observaciones

                for (String[] asistencia : asistencias) {
                    html.append("<tr>");
                    html.append("<td>").append(asistencia[0]).append("</td>");
                    html.append("<td>").append(asistencia[1]).append("</td>");
                    html.append("<td>").append(asistencia[2]).append("</td>");
                    html.append("<td>").append(asistencia[3]).append("</td>");
                    html.append("<td>").append(asistencia[4]).append("</td>");
                    html.append("<td>").append(asistencia[5]).append("</td>");

                    String estado = asistencia[6];
                    String cssClass = "";
                    if (estado.equals("presente")) cssClass = "presente";
                    else if (estado.equals("ausente")) cssClass = "ausente";
                    else if (estado.equals("tardanza")) cssClass = "tardanza";
                    else if (estado.equals("justificado")) cssClass = "justificado";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("<td>").append(asistencia[7] != null ? asistencia[7] : "").append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de asistencias: ").append(asistencias.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

    

        public static String generateLicenciaTable(ArrayList<String[]> licencias) {
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
            html.append(".pendiente { color: #FF9800; font-weight: bold; }");
            html.append(".aprobada { color: #4CAF50; font-weight: bold; }");
            html.append(".rechazada { color: #F44336; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #FF9800; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>📝 Lista de Licencias</h2>");

            if (licencias.isEmpty()) {
                html.append("<p>No hay licencias registradas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Asistencia ID</th>");
                html.append("<th>Tutor</th>");
                html.append("<th>Fecha Clase</th>");
                html.append("<th>Motivo</th>");
                html.append("<th>Estado</th>");
                html.append("<th>Fecha Solicitud</th>");
                html.append("</tr>");

                // licencia[0] = id
                // licencia[1] = tutor_id
                // licencia[2] = tutor_nombre
                // licencia[3] = fecha_licencia
                // licencia[4] = motivo
                // licencia[5] = estado
                // licencia[6] = fecha_solicitud

                for (String[] licencia : licencias) {
                    html.append("<tr>");
                    html.append("<td>").append(licencia[0]).append("</td>");
                    html.append("<td>").append(licencia[1]).append("</td>");
                    html.append("<td><strong>").append(licencia[2]).append("</strong></td>");
                    html.append("<td>").append(licencia[3]).append("</td>");
                    html.append("<td>").append(licencia[4]).append("</td>");

                    String estado = licencia[5];
                    String cssClass = "";
                    if (estado.equals("pendiente")) cssClass = "pendiente";
                    else if (estado.equals("aprobada")) cssClass = "aprobada";
                    else if (estado.equals("rechazada")) cssClass = "rechazada";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("<td>").append(licencia[6]).append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de licencias: ").append(licencias.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

     

        public static String generateReprogramacionTable(ArrayList<String[]> reprogramaciones) {
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
            html.append(".programada { color: #2196F3; font-weight: bold; }");
            html.append(".realizada { color: #4CAF50; font-weight: bold; }");
            html.append(".cancelada { color: #F44336; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #9C27B0; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>🔄 Lista de Reprogramaciones</h2>");

            if (reprogramaciones.isEmpty()) {
                html.append("<p>No hay reprogramaciones registradas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Licencia ID</th>");
                html.append("<th>Tutor</th>");
                html.append("<th>Fecha Licencia</th>");
                html.append("<th>Fecha Original</th>");
                html.append("<th>Fecha Nueva</th>");
                html.append("<th>Estado</th>");
                html.append("<th>Observaciones</th>");
                html.append("</tr>");

                // reprogramacion[0] = id
                // reprogramacion[1] = licencia_id
                // reprogramacion[2] = tutor_nombre
                // reprogramacion[3] = fecha_licencia
                // reprogramacion[4] = fecha_original
                // reprogramacion[5] = fecha_nueva
                // reprogramacion[6] = estado
                // reprogramacion[7] = observaciones

                for (String[] reprog : reprogramaciones) {
                    html.append("<tr>");
                    html.append("<td>").append(reprog[0]).append("</td>");
                    html.append("<td>").append(reprog[1]).append("</td>");
                    html.append("<td><strong>").append(reprog[2]).append("</strong></td>");
                    html.append("<td>").append(reprog[3]).append("</td>");
                    html.append("<td>").append(reprog[4]).append("</td>");
                    html.append("<td><strong>").append(reprog[5]).append("</strong></td>");

                    String estado = reprog[6];
                    String cssClass = "";
                    if (estado.equals("programada")) cssClass = "programada";
                    else if (estado.equals("realizada")) cssClass = "realizada";
                    else if (estado.equals("cancelada")) cssClass = "cancelada";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("<td>").append(reprog[7] != null ? reprog[7] : "").append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de reprogramaciones: ").append(reprogramaciones.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }
        
                public static String generateVentaTable(ArrayList<String[]> ventas) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #3F51B5; color: white; padding: 12px; text-align: left; font-size: 11px; }");
            html.append("td { padding: 8px; border-bottom: 1px solid #ddd; font-size: 11px; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".pendiente { color: #FF9800; font-weight: bold; }");
            html.append(".pagado { color: #4CAF50; font-weight: bold; }");
            html.append(".vencido { color: #F44336; font-weight: bold; }");
            html.append(".monto { text-align: right; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #3F51B5; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>💰 Lista de Ventas</h2>");

            if (ventas.isEmpty()) {
                html.append("<p>No hay ventas registradas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Inscripción ID</th>");
                html.append("<th>Propietario</th>");
                html.append("<th>Tipo</th>");
                html.append("<th>Cuotas</th>");
                html.append("<th>Monto Total</th>");
                html.append("<th>Pagado</th>");
                html.append("<th>Pendiente</th>");
                html.append("<th>Mes</th>");
                html.append("<th>Fecha Venta</th>");
                html.append("<th>Vencimiento</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] venta : ventas) {
                    html.append("<tr>");
                    html.append("<td>").append(venta[0]).append("</td>");
                    html.append("<td><strong>").append(venta[2]).append("</strong></td>");
                    html.append("<td>").append(venta[3]).append("</td>");
                    html.append("<td>").append(venta[4].toUpperCase()).append("</td>");
                    html.append("<td class='monto'>$").append(venta[5]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[6]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[7]).append("</td>");
                    html.append("<td>").append(venta[8]).append("</td>");
                    html.append("<td>").append(venta[9]).append("</td>");
                    html.append("<td>").append(venta[10]).append("</td>");

                    String estado = venta[11];
                    String cssClass = "";
                    if (estado.equals("pendiente")) cssClass = "pendiente";
                    else if (estado.equals("pagado")) cssClass = "pagado";
                    else if (estado.equals("vencido")) cssClass = "vencido";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de ventas: ").append(ventas.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generateVentasPorAlumnoTable(ArrayList<String[]> ventas, String nombreAlumno) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".subtitle { color: #666; font-size: 16px; margin-bottom: 20px; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #3F51B5; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".pendiente { color: #FF9800; font-weight: bold; }");
            html.append(".pagado { color: #4CAF50; font-weight: bold; }");
            html.append(".vencido { color: #F44336; font-weight: bold; }");
            html.append(".monto { text-align: right; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #3F51B5; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>💰 Ventas del Alumno</h2>");
            html.append("<p class='subtitle'>Alumno: <strong>").append(nombreAlumno).append("</strong></p>");

            if (ventas.isEmpty()) {
                html.append("<p>Este alumno no tiene ventas registradas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Propietario</th>");
                html.append("<th>Tipo</th>");
                html.append("<th>Monto Total</th>");
                html.append("<th>Pagado</th>");
                html.append("<th>Pendiente</th>");
                html.append("<th>Mes</th>");
                html.append("<th>Fecha</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] venta : ventas) {
                    html.append("<tr>");
                    html.append("<td>").append(venta[0]).append("</td>");
                    html.append("<td>").append(venta[1]).append("</td>");
                    html.append("<td>").append(venta[2].toUpperCase()).append("</td>");
                    html.append("<td class='monto'>$").append(venta[3]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[4]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[5]).append("</td>");
                    html.append("<td>").append(venta[6]).append("</td>");
                    html.append("<td>").append(venta[7]).append("</td>");

                    String estado = venta[8];
                    String cssClass = "";
                    if (estado.equals("pendiente")) cssClass = "pendiente";
                    else if (estado.equals("pagado")) cssClass = "pagado";
                    else if (estado.equals("vencido")) cssClass = "vencido";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de ventas: ").append(ventas.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generateVentasPorMesTable(ArrayList<String[]> ventas, String mes) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".subtitle { color: #666; font-size: 16px; margin-bottom: 20px; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #3F51B5; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".pendiente { color: #FF9800; font-weight: bold; }");
            html.append(".pagado { color: #4CAF50; font-weight: bold; }");
            html.append(".vencido { color: #F44336; font-weight: bold; }");
            html.append(".monto { text-align: right; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #3F51B5; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>📅 Ventas del Mes</h2>");
            html.append("<p class='subtitle'>Mes: <strong>").append(mes).append("</strong></p>");

            if (ventas.isEmpty()) {
                html.append("<p>No hay ventas para este mes.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Tipo</th>");
                html.append("<th>Monto Total</th>");
                html.append("<th>Pagado</th>");
                html.append("<th>Pendiente</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] venta : ventas) {
                    html.append("<tr>");
                    html.append("<td>").append(venta[0]).append("</td>");
                    html.append("<td><strong>").append(venta[1]).append("</strong></td>");
                    html.append("<td>").append(venta[2].toUpperCase()).append("</td>");
                    html.append("<td class='monto'>$").append(venta[3]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[4]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[5]).append("</td>");

                    String estado = venta[6];
                    String cssClass = "";
                    if (estado.equals("pendiente")) cssClass = "pendiente";
                    else if (estado.equals("pagado")) cssClass = "pagado";
                    else if (estado.equals("vencido")) cssClass = "vencido";
                    html.append("<td class='").append(cssClass).append("'>").append(estado.toUpperCase()).append("</td>");

                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de ventas: ").append(ventas.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generateVentasVencidasTable(ArrayList<String[]> ventas) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #F44336; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #F44336; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #ffebee; }");
            html.append(".monto { text-align: right; font-weight: bold; color: #F44336; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #F44336; }");
            html.append(".alerta { background-color: #ffebee; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>⚠️ Ventas Vencidas</h2>");

            if (ventas.isEmpty()) {
                html.append("<p style='color: #4CAF50;'>✓ No hay ventas vencidas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Teléfono</th>");
                html.append("<th>Saldo Pendiente</th>");
                html.append("<th>Mes</th>");
                html.append("<th>Fecha Vencimiento</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] venta : ventas) {
                    html.append("<tr class='alerta'>");
                    html.append("<td>").append(venta[0]).append("</td>");
                    html.append("<td><strong>").append(venta[1]).append("</strong></td>");
                    html.append("<td>").append(venta[2]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[3]).append("</td>");
                    html.append("<td>").append(venta[4]).append("</td>");
                    html.append("<td>").append(venta[5]).append("</td>");
                    html.append("<td style='color: #F44336; font-weight: bold;'>").append(venta[6].toUpperCase()).append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>⚠️ Total de ventas vencidas: ").append(ventas.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generateVentasPendientesTable(ArrayList<String[]> ventas) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #FF9800; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #FF9800; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #fff3e0; }");
            html.append(".monto { text-align: right; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #FF9800; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>⏳ Ventas Pendientes</h2>");

            if (ventas.isEmpty()) {
                html.append("<p style='color: #4CAF50;'>✓ No hay ventas pendientes.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Tipo</th>");
                html.append("<th>Monto Total</th>");
                html.append("<th>Pagado</th>");
                html.append("<th>Pendiente</th>");
                html.append("<th>Mes</th>");
                html.append("<th>Fecha</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] venta : ventas) {
                    html.append("<tr>");
                    html.append("<td>").append(venta[0]).append("</td>");
                    html.append("<td><strong>").append(venta[1]).append("</strong></td>");
                    html.append("<td>").append(venta[2].toUpperCase()).append("</td>");
                    html.append("<td class='monto'>$").append(venta[3]).append("</td>");
                    html.append("<td class='monto'>$").append(venta[4]).append("</td>");
                    html.append("<td class='monto' style='color: #FF9800;'>$").append(venta[5]).append("</td>");
                    html.append("<td>").append(venta[6]).append("</td>");
                    html.append("<td>").append(venta[7]).append("</td>");
                    html.append("<td style='color: #FF9800; font-weight: bold;'>").append(venta[8].toUpperCase()).append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de ventas pendientes: ").append(ventas.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generateVentasPagadasTable(ArrayList<String[]> ventas) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #4CAF50; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #4CAF50; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #e8f5e9; }");
            html.append(".monto { text-align: right; font-weight: bold; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #4CAF50; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>✅ Ventas Pagadas</h2>");

            if (ventas.isEmpty()) {
                html.append("<p>No hay ventas pagadas.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Tipo</th>");
                html.append("<th>Monto Total</th>");
                html.append("<th>Mes</th>");
                html.append("<th>Fecha</th>");
                html.append("<th>Estado</th>");
                html.append("</tr>");

                for (String[] venta : ventas) {
                    html.append("<tr>");
                    html.append("<td>").append(venta[0]).append("</td>");
                    html.append("<td><strong>").append(venta[1]).append("</strong></td>");
                    html.append("<td>").append(venta[2].toUpperCase()).append("</td>");
                    html.append("<td class='monto'>$").append(venta[3]).append("</td>");
                    html.append("<td>").append(venta[6]).append("</td>");
                    html.append("<td>").append(venta[7]).append("</td>");
                    html.append("<td style='color: #4CAF50; font-weight: bold;'>✓ ").append(venta[8].toUpperCase()).append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de ventas pagadas: ").append(ventas.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generateEstadisticasVentas(String[] stats) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".stats-container { display: grid; grid-template-columns: repeat(2, 1fr); gap: 15px; margin-top: 20px; }");
            html.append(".stat-card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append(".stat-label { color: #666; font-size: 14px; margin-bottom: 5px; }");
            html.append(".stat-value { color: #333; font-size: 24px; font-weight: bold; }");
            html.append(".vendido { color: #3F51B5; }");
            html.append(".pagado { color: #4CAF50; }");
            html.append(".pendiente { color: #FF9800; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>📊 Estadísticas de Ventas</h2>");

            html.append("<div class='stats-container'>");
            
            html.append("<div class='stat-card'>");
            html.append("<div class='stat-label'>Total de Ventas</div>");
            html.append("<div class='stat-value'>").append(stats[0]).append("</div>");
            html.append("</div>");

            html.append("<div class='stat-card'>");
            html.append("<div class='stat-label'>Total Vendido</div>");
            html.append("<div class='stat-value vendido'>$").append(stats[1]).append("</div>");
            html.append("</div>");

            html.append("<div class='stat-card'>");
            html.append("<div class='stat-label'>Total Pagado</div>");
            html.append("<div class='stat-value pagado'>$").append(stats[2]).append("</div>");
            html.append("</div>");

            html.append("<div class='stat-card'>");
            html.append("<div class='stat-label'>Total Pendiente</div>");
            html.append("<div class='stat-value pendiente'>$").append(stats[3]).append("</div>");
            html.append("</div>");

            html.append("<div class='stat-card'>");
            html.append("<div class='stat-label'>Ventas Pendientes</div>");
            html.append("<div class='stat-value pendiente'>").append(stats[4]).append("</div>");
            html.append("</div>");

            html.append("<div class='stat-card'>");
            html.append("<div class='stat-label'>Ventas Pagadas</div>");
            html.append("<div class='stat-value pagado'>").append(stats[5]).append("</div>");
            html.append("</div>");

            html.append("</div>");

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        // ==================== MÉTODOS PARA PAGO ====================

        public static String generatePagoTable(ArrayList<String[]> pagos) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #4CAF50; color: white; padding: 12px; text-align: left; font-size: 11px; }");
            html.append("td { padding: 8px; border-bottom: 1px solid #ddd; font-size: 11px; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".monto { text-align: right; font-weight: bold; color: #4CAF50; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #4CAF50; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>💵 Lista de Pagos</h2>");

            if (pagos.isEmpty()) {
                html.append("<p>No hay pagos registrados.</p>");
            } else {
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Venta ID</th>");
                html.append("<th>Alumno</th>");
                html.append("<th>Mes</th>");
                html.append("<th>Monto</th>");
                html.append("<th>Fecha Pago</th>");
                html.append("<th>Método</th>");
                html.append("<th>Observaciones</th>");
                html.append("</tr>");

                for (String[] pago : pagos) {
                    html.append("<tr>");
                    html.append("<td>").append(pago[0]).append("</td>");
                    html.append("<td>").append(pago[1]).append("</td>");
                    html.append("<td><strong>").append(pago[2]).append("</strong></td>");
                    html.append("<td>").append(pago[3]).append("</td>");
                    html.append("<td class='monto'>$").append(pago[4]).append("</td>");
                    html.append("<td>").append(pago[5]).append("</td>");
                    html.append("<td>").append(pago[6]).append("</td>");
                    html.append("<td>").append(pago[7]).append("</td>");
                    html.append("</tr>");
                }

                html.append("</table>");
                html.append("<p class='total'>Total de pagos: ").append(pagos.size()).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generatePagosPorVentaTable(ArrayList<String[]> pagos, int ventaId) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".subtitle { color: #666; font-size: 16px; margin-bottom: 20px; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #4CAF50; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".monto { text-align: right; font-weight: bold; color: #4CAF50; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #4CAF50; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>💵 Pagos de la Venta</h2>");
            html.append("<p class='subtitle'>Venta ID: <strong>#").append(ventaId).append("</strong></p>");

            if (pagos.isEmpty()) {
                html.append("<p>No hay pagos registrados para esta venta.</p>");
            } else {
                double totalPagado = 0;
                
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Monto</th>");
                html.append("<th>Fecha Pago</th>");
                html.append("<th>Método</th>");
                html.append("<th>Observaciones</th>");
                html.append("<th>Registrado Por</th>");
                html.append("</tr>");

                for (String[] pago : pagos) {
                    html.append("<tr>");
                    html.append("<td>").append(pago[0]).append("</td>");
                    html.append("<td class='monto'>$").append(pago[1]).append("</td>");
                    html.append("<td>").append(pago[2]).append("</td>");
                    html.append("<td>").append(pago[3]).append("</td>");
                    html.append("<td>").append(pago[4]).append("</td>");
                    html.append("<td>").append(pago[5]).append("</td>");
                    html.append("</tr>");
                    
                    totalPagado += Double.parseDouble(pago[1]);
                }

                html.append("</table>");
                html.append("<p class='total'>Total de pagos: ").append(pagos.size()).append("</p>");
                html.append("<p class='total'>Monto total pagado: $").append(String.format("%.2f", totalPagado)).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

        public static String generatePagosPorAlumnoTable(ArrayList<String[]> pagos, String nombreAlumno) {
            StringBuilder html = new StringBuilder();

            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
            html.append("h2 { color: #333; }");
            html.append(".subtitle { color: #666; font-size: 16px; margin-bottom: 20px; }");
            html.append("table { border-collapse: collapse; width: 100%; background-color: white; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
            html.append("th { background-color: #4CAF50; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }");
            html.append("tr:hover { background-color: #f5f5f5; }");
            html.append(".monto { text-align: right; font-weight: bold; color: #4CAF50; }");
            html.append(".total { margin-top: 20px; font-weight: bold; color: #4CAF50; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<h2>💵 Historial de Pagos</h2>");
            html.append("<p class='subtitle'>Alumno: <strong>").append(nombreAlumno).append("</strong></p>");

            if (pagos.isEmpty()) {
                html.append("<p>Este alumno no tiene pagos registrados.</p>");
            } else {
                double totalPagado = 0;
                
                html.append("<table>");
                html.append("<tr>");
                html.append("<th>ID</th>");
                html.append("<th>Venta ID</th>");
                html.append("<th>Mes</th>");
                html.append("<th>Monto</th>");
                html.append("<th>Fecha Pago</th>");
                html.append("<th>Método</th>");
                html.append("</tr>");

                for (String[] pago : pagos) {
                    html.append("<tr>");
                    html.append("<td>").append(pago[0]).append("</td>");
                    html.append("<td>#").append(pago[1]).append("</td>");
                    html.append("<td>").append(pago[2]).append("</td>");
                    html.append("<td class='monto'>$").append(pago[3]).append("</td>");
                    html.append("<td>").append(pago[4]).append("</td>");
                    html.append("<td>").append(pago[5]).append("</td>");
                    html.append("</tr>");
                    
                    totalPagado += Double.parseDouble(pago[3]);
                }

                html.append("</table>");
                html.append("<p class='total'>Total de pagos: ").append(pagos.size()).append("</p>");
                html.append("<p class='total'>Monto total pagado: $").append(String.format("%.2f", totalPagado)).append("</p>");
            }

            html.append("</body>");
            html.append("</html>");

            return html.toString();
        }

}