package utils;


public class HelpGenerator {
    
    public static String generateHelpHTML() {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f5f5f5; }");
        html.append("h1 { color: #2196F3; text-align: center; }");
        html.append("h2 { color: #333; border-bottom: 2px solid #2196F3; padding-bottom: 5px; margin-top: 30px; }");
        html.append(".command { background: white; padding: 15px; margin: 10px 0; border-radius: 5px; ");
        html.append("box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append(".command-title { color: #2196F3; font-weight: bold; font-size: 16px; }");
        html.append(".command-syntax { background: #e3f2fd; padding: 10px; margin: 10px 0; ");
        html.append("border-left: 4px solid #2196F3; font-family: monospace; }");
        html.append(".command-desc { color: #666; margin: 5px 0; }");
        html.append(".example { background: #f5f5f5; padding: 8px; margin: 5px 0; font-family: monospace; font-size: 13px; }");
        html.append(".category { background: #e8f5e9; padding: 3px 8px; border-radius: 3px; font-size: 12px; ");
        html.append("display: inline-block; margin: 5px 0; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        
        html.append("<h1>📚 Sistema de Gestión Educativa - Comandos Disponibles</h1>");
        
        // ========== GESTIÓN DE USUARIOS ==========
        
        
        // ========== GESTIÓN DE ALUMNOS ==========
        html.append("<h2>🎓 Gestión de Alumnos</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>alumno add</div>");
        html.append("<div class='command-desc'>Registrar un nuevo alumno</div>");
        html.append("<div class='command-syntax'>alumno add &lt;nombre, apellido, email, telefono, fecha_nacimiento, direccion, estado, grado_escolar, fecha_ingreso&gt;</div>");
        html.append("<div class='example'><strong>Ejemplo:</strong> alumno add &lt;Maria, Garcia, maria@mail.com, 76098765, 2005-03-20, Calle 1, activo, 6to primaria, 2024-01-15&gt;</div>");
        html.append("<div class='command-title'>alumno modify</div>");
        html.append("<div class='command-syntax'>alumno modify &lt;email, nombre, apellido, telefono, fecha_nacimiento, direccion, estado, grado_escolar, fecha_ingreso&gt;</div>");
        html.append("<div class='command-desc'>Modificar un alumno</div>");
        html.append("<div class='command-title'>alumno get</div>");
        html.append("<div class='command-syntax'>alumno get &lt;&gt;</div>");
        html.append("<div class='command-desc'>Listar alumnos</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>alumno modify/ get</div>");
        html.append("<div class='command-desc'>Modificar, eliminar o listar alumnos (misma sintaxis que usuarios)</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE TUTORES ==========
        html.append("<h2>👨‍🏫 Gestión de Tutores</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>tutor add</div>");
        html.append("<div class='command-desc'>Agregar un nuevo tutor</div>");
        html.append("<div class='command-syntax'>tutor add &lt;nombre, apellido, email, telefono, fecha_nacimiento, direccion, estado&gt;</div>");
        html.append("<div class='command-title'>tutor modify/div>");
        html.append("<div class='command-desc'>Modificar un tutor</div>");
        html.append("<div class='command-syntax'>tutor modify &lt;nombre, apellido, email, telefono, fecha_nacimiento, direccion, estado&gt;</div>");
        html.append("<div class='command-title'>tutor get/div>");
        html.append("<div class='command-desc'>Listar Tutores</div>");
        html.append("<div class='command-syntax'>tutor get &lt;&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>tutor modify / get</div>");
        html.append("<div class='command-desc'>Modificar, eliminar o listar tutores</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE HORARIOS ==========
        html.append("<h2>🕐 Gestión de Horarios</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>horario add</div>");
        html.append("<div class='command-desc'>Crear un nuevo horario</div>");
        html.append("<div class='command-syntax'>horario add &lt;dia_semana, hora_inicio, hora_fin, estado&gt;</div>");
        html.append("<div class='example'><strong>Ejemplo:</strong> horario add &lt;lunes, 08:00:00, 10:00:00, activo&gt;</div>");
        html.append("<div class='command-desc'>Listar horarios</div>");
        html.append("<div class='command-syntax'>horario get &lt;&gt;</div>");
        html.append("<div class='command-desc'>Listar Horarios</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>horario modify / get</div>");
        html.append("<div class='command-desc'>Modificar, eliminar o listar horarios</div>");
        html.append("</div>");
        
        // ========== ASIGNACIÓN TUTOR-HORARIO ==========
        html.append("<h2>📅 Asignación Tutor-Horario</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>tutorhorario add</div>");
        html.append("<div class='command-desc'>Asignar un horario a un tutor</div>");
        html.append("<div class='command-syntax'>tutorhorario add &lt;tutor_id, horario_id, fecha_asignacion&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>tutorhorario get</div>");
        html.append("<div class='command-desc'>Listar los horarios de los tutores</div>");
        html.append("<div class='command-syntax'>tutorhorario get &lt;&gt;</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE INSCRIPCIONES ==========
        html.append("<h2>📝 Gestión de Inscripciones</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>inscripcion add</div>");
        html.append("<div class='command-desc'>Registrar una nueva inscripción</div>");
        html.append("<div class='command-syntax'>inscripcion add &lt;servicio_id, alumno_id, tutor_id, fecha_inscripcion, direccion, foto_url, estado, observaciones&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>inscripcion listarporalumno</div>");
        html.append("<div class='command-desc'>Ver inscripciones de un alumno específico</div>");
        html.append("<div class='command-syntax'>inscripcion listarporalumno &lt;alumno_id&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>inscripcion listarportutor</div>");
        html.append("<div class='command-desc'>Ver alumnos asignados a un tutor</div>");
        html.append("<div class='command-syntax'>inscripcion listarportutor &lt;tutor_id&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>inscripcion listarporservicio</div>");
        html.append("<div class='command-desc'>Ver inscripciones por servicio/modalidad</div>");
        html.append("<div class='command-syntax'>inscripcion listarporservicio &lt;servicio_id&gt;</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE SERVICIOS ==========
        html.append("<h2>📚 Gestión de Servicios</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>servicio get</div>");
        html.append("<div class='command-desc'>Listar todos los servicios/modalidades disponibles</div>");
        html.append("<div class='command-syntax'>servicio get &lt;&gt</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>servicio activar / desactivar</div>");
        html.append("<div class='command-desc'>Activar o desactivar un servicio</div>");
        html.append("<div class='command-syntax'>servicio activar &lt;id&gt;<br>servicio desactivar &lt;id&gt;</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE ASISTENCIAS ==========
        html.append("<h2>✅ Gestión de Asistencias</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>asistencia add</div>");
        html.append("<div class='command-desc'>Registrar asistencia de un alumno</div>");
        html.append("<div class='command-syntax'>asistencia add &lt;inscripcion_id, fecha, estado, observaciones&gt;</div>");
        html.append("<div class='example'><strong>Ejemplo:</strong> asistencia add &lt;1, 2024-11-13, presente, Asistio puntualmente&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>asistencia get</div>");
        html.append("<div class='command-desc'>listar asistencias</div>");
        html.append("<div class='command-syntax'>asistencia get &lt;&gt;</div>");
        html.append("</div>");
        
        // ========== INFORMES DE CLASE ==========
        html.append("<h2>📋 Informes de Clase</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>informeclase add</div>");
        html.append("<div class='command-desc'>Crear un informe detallado de clase</div>");
        html.append("<div class='command-syntax'>informeclase add &lt;inscripcion_id, fecha, temas_vistos, tareas_asignadas, nivel_comprension, participacion, cumplimiento_tareas, calificacion, resumen, logros, dificultades, recomendaciones, observaciones, estado&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>informeclase modify / get</div>");
        html.append("<div class='command-desc'>Modificar, eliminar o listar informes de clase</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE LICENCIAS ==========
        html.append("<h2>📝 Gestión de Licencias</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>licencia add</div>");
        html.append("<div class='command-desc'>Solicitar una licencia para un tutor</div>");
        html.append("<div class='command-syntax'>licencia add &lt;tutor_id, fecha_licencia, motivo, estado&gt;</div>");
        
        html.append("<div class='command-title'>licencia get</div>");
        html.append("<div class='command-desc'>listar licencia</div>");
        html.append("<div class='command-syntax'>licencia get &lt;&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>licencia modify /get</div>");
        html.append("<div class='command-desc'>Modificar o listar licencias</div>");
        html.append("</div>");
        
        // ========== REPROGRAMACIONES ==========
        html.append("<h2>🔄 Reprogramaciones</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>reprogramacion add</div>");
        html.append("<div class='command-desc'>Reprogramar una clase por licencia</div>");
        html.append("<div class='command-syntax'>reprogramacion add &lt;licencia_id, fecha_original, fecha_nueva, estado, observaciones&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>reprogramacion marcarrealizada</div>");
        html.append("<div class='command-desc'>Marcar una reprogramación como realizada</div>");
        html.append("<div class='command-syntax'>reprogramacion marcarrealizada &lt;id&gt;</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE VENTAS ==========
        html.append("<h2>💰 Gestión de Ventas</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>venta add</div>");
        html.append("<div class='command-desc'>Registrar una nueva venta</div>");
        html.append("<div class='command-syntax'>venta add &lt;alumno_id, propietario_id, tipo_venta, monto_total, monto_pagado, mes_correspondiente, fecha_venta, fecha_vencimiento&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>venta listarporalumnoventa</div>");
        html.append("<div class='command-desc'>Ver ventas de un alumno</div>");
        html.append("<div class='command-syntax'>venta listarporalumnoventa &lt;alumno_id&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>venta listarvencidas / listarpendientesventa / listarpagadas</div>");
        html.append("<div class='command-desc'>Listar ventas por estado</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>venta estadisticas</div>");
        html.append("<div class='command-desc'>Ver estadísticas generales de ventas</div>");
        html.append("<div class='command-syntax'>venta estadisticas &lt;&gt;</div>");
        html.append("</div>");
        
        // ========== GESTIÓN DE PAGOS ==========
        html.append("<h2>💵 Gestión de Pagos</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>pago add</div>");
        html.append("<div class='command-desc'>Registrar un pago</div>");
        html.append("<div class='command-syntax'>pago add &lt;venta_id, monto, metodo_pago, observaciones, registrado_por&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>pago listarporventa</div>");
        html.append("<div class='command-desc'>Ver pagos de una venta específica</div>");
        html.append("<div class='command-syntax'>pago listarporventa &lt;venta_id&gt;</div>");
        html.append("</div>");
        
        // ========== REPORTES ==========
        html.append("<h2>📊 Reportes y Gráficas</h2>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>reporte asistencias</div>");
        html.append("<div class='command-desc'>Gráfica de estadísticas de asistencia</div>");
        html.append("<div class='command-syntax'>reporte asistencias &lt;&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>reporte inscripciones</div>");
        html.append("<div class='command-desc'>Gráfica de inscripciones por mes</div>");
        html.append("<div class='command-syntax'>reporte inscripciones &lt;&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>reporte servicios</div>");
        html.append("<div class='command-desc'>Gráfica de alumnos por servicio</div>");
        html.append("<div class='command-syntax'>reporte servicios &lt;&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>reporte ventas</div>");
        html.append("<div class='command-desc'>Gráfica de ventas por estado</div>");
        html.append("<div class='command-syntax'>reporte ventas &lt;&gt;</div>");
        html.append("</div>");
        
        html.append("<div class='command'>");
        html.append("<div class='command-title'>reporte pagos</div>");
        html.append("<div class='command-desc'>Gráfica de pagos por mes</div>");
        html.append("<div class='command-syntax'>reporte pagos &lt;&gt;</div>");
        html.append("</div>");
        
        // NOTA FINAL
        html.append("<div style='background: #fff3cd; padding: 15px; margin-top: 30px; border-left: 4px solid #ff9800; border-radius: 5px;'>");
        html.append("<h3 style='margin-top: 0; color: #ff6f00;'>📌 Notas Importantes:</h3>");
        html.append("<ul>");
        html.append("<li>Todos los parámetros deben estar separados por comas</li>");
        html.append("<li>Las fechas deben seguir el formato: YYYY-MM-DD (ej: 2024-11-13)</li>");
        html.append("<li>Las horas deben seguir el formato: HH:MM:SS (ej: 14:30:00)</li>");
        html.append("<li>Los estados comunes son: activo, inactivo, pendiente, aprobado, cancelado</li>");
        html.append("<li>Para más ayuda, envía el comando <strong>help</strong> nuevamente</li>");
        html.append("</ul>");
        html.append("</div>");
        
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
}