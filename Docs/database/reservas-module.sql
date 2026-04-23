CREATE TABLE IF NOT EXISTS booking_resources (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    category VARCHAR(80) NOT NULL,
    location VARCHAR(120) NOT NULL,
    description VARCHAR(255),
    slot_duration_minutes INT NOT NULL DEFAULT 30,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    public_resource BOOLEAN NOT NULL DEFAULT TRUE,
    owner_user_id INT NULL,
    CONSTRAINT fk_booking_resources_owner FOREIGN KEY (owner_user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    resource_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    purpose VARCHAR(120) NOT NULL,
    notes VARCHAR(255),
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVA',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bookings_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_bookings_resource FOREIGN KEY (resource_id) REFERENCES booking_resources(id)
);

INSERT INTO booking_resources (name, category, location, description, slot_duration_minutes, active, public_resource, owner_user_id)
VALUES
    ('Consulta de medicina general', 'Cita medica', 'Centro de salud Norte', 'Revision general, recetas y seguimiento de sintomas.', 30, TRUE, TRUE, NULL),
    ('Pediatria', 'Cita medica', 'Centro de salud Infantil', 'Atencion pediatrica para menores y controles basicos.', 30, TRUE, TRUE, NULL),
    ('Psicologia clinica', 'Cita medica', 'Centro integral de bienestar', 'Atencion psicologica y seguimiento emocional.', 45, TRUE, TRUE, NULL),
    ('Atencion en comisaria', 'Comisaria', 'Comisaria Centro', 'Tramites, denuncias y consultas presenciales.', 20, TRUE, TRUE, NULL),
    ('Renovacion de documentacion', 'Comisaria', 'Comisaria Distrito Norte', 'Reserva para tramites administrativos y documentacion.', 20, TRUE, TRUE, NULL),
    ('Trabajo social', 'Atencion social', 'Oficina municipal', 'Orientacion social y derivacion de servicios.', 40, TRUE, TRUE, NULL);
