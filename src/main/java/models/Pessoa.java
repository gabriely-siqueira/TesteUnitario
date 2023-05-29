package models;

import enums.EnumSexo;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Pessoa {
	
	private Integer id;
	@NonNull
	private String nome;
	@NonNull
	private Integer idade;
	@NonNull
	private EnumSexo sexo;
	

}
