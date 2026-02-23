| Campo | Tipo | ¿En API? | Razón |
| ----- | ----- | ----- | ----- |
| id\_usuario | int | Si | Esencial para identificar al usuario |
|nombre | varchar | Sí | Mostrar información del usuario |
|correo\_electronico | varchar | Sí | Identificación visible |
|contraseña | varchar | NO | Seguridad, nunca se expone |
|fecha\_nacimiento | date | NO | No afecta al sistema de reservas |
|tipo\_usuario | enum | NO | Se valida internamente para permisos |
|teléfono guardia | varchar | NO | No relevante para realizar reservas |
|dirección | varchar | NO | No necesario para reservar |
|teléfono móvil | varchar | NO | No necesario para la operación |
|fotografía | varchar | NO | No necesario para reservas |
|id\_recurso | int | Sí | Identificador del recurso |
|nombre recurso | varchar | Sí | Mostrar qué se reserva |
|descripción | varchar | NO | Información opcional no crítica |
|ubicación | varchar | Sí | Información útil para el usuario |
|capacidad | int | Sí | Validar número de plazas |
|id\_horario | int | NO | Gestión interna de disponibilidad |
|dia semana | enum | NO | Se usa internamente para validación |
|hora\_inicio\_horario | time | NO | Validación interna de disponibilidad |
|hora\_fin\_horario | time | NO | Validación interna de disponibilidad |
|id\_reserva\_local | int | Sí | Identificador único de la reserva |
|id\_usuario\_reserva | int | NO | Se obtiene del usuario autenticado |
|fecha | date | Sí | Día reservado |
|hora\_inicio | time | Sí | Hora de inicio |
|hora\_fin | time | Sí | Hora de finalización |
|número plazas | int | Sí | Control de capacidad |
|coste | decimal | Sí | Información económica visible |
|motivo | varchar | Sí | Información opcional |
|observaciones | varchar | Sí | Información adicional opcional |
|creada\_en | timestamp | NO | Auditoría interna |
|actualizada\_en | timestamp | NO | Auditoría interna |
|deleted\_at | timestamp | NO | Soft delete interno |

