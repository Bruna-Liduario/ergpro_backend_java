package com.ergproapontamento.ergpro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.CargoFuncionarioDTO;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.CargoFuncionarioRepository;
import com.ergproapontamento.ergpro.repository.FuncionarioRepository;
import com.ergproapontamento.ergpro.repository.entity.CargoFuncionario;

@Service
@Transactional
public class CargoFuncionarioService {
	
	@Autowired
	private CargoFuncionarioRepository cargoFuncionarioRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	//listar
	public List<CargoFuncionarioDTO> listarCargos () {
		List<CargoFuncionario> cargoFuncionario = cargoFuncionarioRepository.findAll();
		return cargoFuncionario.stream()
				.map(e -> e.convertEntityToDto())
				.collect(Collectors.toList());
	}
	
	
	//buscar
	public  CargoFuncionarioDTO buscarCargos(Long id) throws NotFoundException {
		CargoFuncionario cargoFuncionario = cargoFuncionarioRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cargo não encontrado: " + id));
		return cargoFuncionario.convertEntityToDto();
	}
	
	public List<String> getNomesCargos() {
        return cargoFuncionarioRepository.findAllNomes();
    }
	
	
	//salvar
	public CargoFuncionarioDTO salvarCargo(CargoFuncionarioDTO cargoFuncionarioDTO) throws ValidacoesException {
		validarCargoFuncionario(cargoFuncionarioDTO);
		
		CargoFuncionario cargoFuncionario = convertDtoToEntity(cargoFuncionarioDTO);
		cargoFuncionario = cargoFuncionarioRepository.save(cargoFuncionario);
			
		return cargoFuncionario.convertEntityToDto();
	}
	
	
	//atualizar
	public CargoFuncionarioDTO atualizarCargo(CargoFuncionarioDTO cargoFuncionarioDTO) throws ValidacoesException, NotFoundException {
		validarCargoFuncionario(cargoFuncionarioDTO);
		validarCargosExistentes(cargoFuncionarioDTO.getId());
		
		CargoFuncionario cargoFuncionario = convertDtoToEntity(cargoFuncionarioDTO);
		cargoFuncionario = cargoFuncionarioRepository.save(cargoFuncionario);
		
		return cargoFuncionario.convertEntityToDto();		
	}
	
	
	//deletar
	public void excluirCargo (Long id) throws NotFoundException, ValidacoesException {
		validarPossibilidadeExclusaoCargo(id);
		validarCargosExistentes(id);
		cargoFuncionarioRepository.deleteById(id);
	}
	
	
	
	
	
	
	public void validarCargoFuncionario (CargoFuncionarioDTO cargoFuncionarioDto) throws ValidacoesException {
		if(cargoFuncionarioDto.getDescricao() == null || cargoFuncionarioDto.getDescricao().isEmpty()) {
			throw new ValidacoesException("Campo Obrigatório");
		}
	}
	
	private void validarCargosExistentes(Long id) throws NotFoundException {
        if (!cargoFuncionarioRepository.existsById(id)) {
            throw new NotFoundException("Cargo não encontrado com o ID: " + id);
        }
    }
	
	public boolean validarPossibilidadeExclusaoCargo(Long id) throws ValidacoesException {		
		if(funcionarioRepository.existsByCargosId(id)) {
	        throw new ValidacoesException("Não é possível excluir o Cargo pois existem funcionários associados a ele");
	    } 
	    return true;
	}
	
	
	
	
	
	private CargoFuncionario convertDtoToEntity(CargoFuncionarioDTO cargoFuncionarioDto) {
		CargoFuncionario cargoFuncionario = new CargoFuncionario();
		cargoFuncionario.setId(cargoFuncionarioDto.getId());
		cargoFuncionario.setDescricao(cargoFuncionarioDto.getDescricao());
		
		return cargoFuncionario;
	}



}
