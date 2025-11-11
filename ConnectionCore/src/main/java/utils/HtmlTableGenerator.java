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
               html.append("<th>Dirección</th>");
               html.append("<th>Foto</th>");
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

                   // Dirección
                   String direccion = inscripcion[8] != null ? inscripcion[8] : "N/A";
                   html.append("<td class='truncate' title='").append(direccion).append("'>").append(direccion).append("</td>");

                   // Foto
                   String foto = inscripcion[9] != null && !inscripcion[9].isEmpty() ? "✓ Sí" : "✗ No";
                   html.append("<td>").append(foto).append("</td>");

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

        // Tabla de alumnos por servicio



}