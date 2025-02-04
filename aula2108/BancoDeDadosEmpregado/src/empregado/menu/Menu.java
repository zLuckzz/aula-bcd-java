package empregado.menu;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

import java.util.List;

import empregado.dao.DepartamentoDAO;
import empregado.dao.EmpregadoDAO;
import empregado.entidade.Departamento;
import empregado.entidade.Empregado;
import empregado.excecao.OpcaoInvalidaException;

public class Menu {

	// método para exibir  o menu da aplicação
	public void menu() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if(opcao < 1 || opcao > 7) {
					throw new OpcaoInvalidaException("Opção inválida");
				} else {
					switch(opcao) {
						case 1:
							cadastrarDepartamento();
							break;
						case 2:
							cadastrarEmpregado();
							break;
						case 3:
							pesquisarEmpregado();
							break;
						case 4:
							listarEmpregado();
							break;
						case 5:
							atualizarEmpregado();
							break;
						case 6:
							excluirEmpregado();
							break;							
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Você deve digitar um número\n" + e);
			}
			catch(OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage());
			}
		} while(opcao != 7);		
	}
	
	private void excluirEmpregado() {
		// TODO Auto-generated method stub
		
	}

	private void atualizarEmpregado() {
		// TODO Auto-generated method stub
		
	}

	private void listarEmpregado() {
		// TODO Auto-generated method stub
		
	}

	private void pesquisarEmpregado() {
		// TODO Auto-generated method stub
		
	}

	private void cadastrarEmpregado() {
		DepartamentoDAO daoDep = new DepartamentoDAO();
		EmpregadoDAO daoEmp = new EmpregadoDAO();
		int id, idDep;
		String nome;
		double salario;
		
		id = parseInt(showInputDialog("ID"));
		Empregado empregado = daoEmp.pesquisar(id);
		if(empregado != null) {
			showMessageDialog(null, "Empregado cadastrado!");
		}
		else {
			List<Departamento> lista = daoDep.listar();
			String aux ="";
			for (Departamento d: lista) {
				aux += d.getId() + " " + d.getNome() + "\n";
			}
			nome = showInputDialog("Nome");
			salario = parseDouble(showInputDialog("Salário"));
			idDep = parseInt(showInputDialog(aux));
			empregado = new Empregado(id, nome, salario, lista.get(idDep-1));
			daoEmp.inserir(empregado);
		}
		
	}


	private void cadastrarDepartamento() {
		int id = parseInt(showInputDialog("ID"));
		String nome = showInputDialog("Nome do departamento");
		Departamento departamento = new Departamento(id, nome);
		DepartamentoDAO dao = new DepartamentoDAO();
		if(dao.pesquisar(departamento)) {
			showMessageDialog(null, "Departamento já está cadastrado");
		} else {
			dao.inserir(departamento);
		}
	}

	// método para gerar o menu da aplicação
	private String gerarMenu() {
		String menu = "CONTROLE DE EMPREGADOS - ESCOLHA UMA OPÇÃO\n";
		menu += "1. Cadadastrar departamento\n";
		menu += "2. Cadadastrar empregado\n";
		menu += "3. Pesquisar empregado\n";
		menu += "4. Listar empregado\n";
		menu += "5. Atualizar empregado\n";
		menu += "6. Excluir empregado\n";
		menu += "7. Finalizar\n";
		return menu;
	}
}
