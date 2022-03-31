CREATE OR REPLACE FUNCTION validaChavePessoa()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
AS $$
declare existe integer;

BEGIN
		existe = (select count (1) from pessoa_medica where id = NEW.pessoa_id);
	if (existe <= 0) then
		existe = (select count(1) from pessoa_pac where id = NEW.pessoa_id);
	if (existe <= 0) then
		existe = (select count(1) from funcionario where id = NEW.pessoa_id);
	if (existe <= 0) then
		RAISE EXCEPTION 'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
	 end if;
	end if;
	end if;
 return new;
END;
$$