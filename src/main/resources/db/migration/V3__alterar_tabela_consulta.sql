ALTER TABLE tb_consulta MODIFY COLUMN data_consulta DATE NOT NULL;
ALTER TABLE tb_consulta ADD COLUMN horario_consulta TIME NOT NULL AFTER data_consulta;
