package application;

import java.lang.reflect.Field;

public class PrintingFunctionModuleManagement {

	private String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	private String MYSQL_UNAME = "root";
	private String MYSQL_PASS = "masiqi93";


	private String L_P = "(";
	private String R_P = ")";
	private String SPACE = " ";
	private String Q = "\"";
	private String L_B = "{";
	private String R_B = "}";

	public void printDataClassGettersAndSetters(Object instance) {
		Field f[] = (instance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			StringBuilder sb = new StringBuilder();
			char varChar[] = varName.toCharArray();
			// generate setter
			varChar[0] = Character.toUpperCase(varChar[0]);
			sb.append("public void ");
			sb.append("set");
			sb.append(varChar);
			sb.append("(");
			sb.append(varType);
			sb.append(" ");
			sb.append(varName);
			sb.append(")");
			sb.append(" ");
			sb.append("{");
			sb.append("\n");
			sb.append("\t");
			sb.append("this.");
			sb.append(varName);
			sb.append(" = ");
			sb.append(varName);
			sb.append(";\n");
			sb.append("}");

			System.out.println(sb.toString());
			System.out.println();

			StringBuilder sb1 = new StringBuilder();
			sb1.append("public ");
			sb1.append(varType);
			sb1.append(" ");
			sb1.append("get");
			sb1.append(varChar);
			sb1.append("() {\n\t");
			sb1.append("return ");
			sb1.append(varName);
			sb1.append(";\n");
			sb1.append("}");

			System.out.println(sb1.toString());
			System.out.println();
		}
	}
	
public void printDataClassCreateHybrid(Object contacts, Object leads) {
		
		String className1 = contacts.getClass().getSimpleName();
		String databaseTableName1 = className1.toLowerCase();
		String classInstance1 = className1.toLowerCase().substring(0, className1.length()-1);
		String classInstanceFirstUpper1 = makeFirstLetterUpperCase(classInstance1);
		
		String className2 = leads.getClass().getSimpleName();
		String databaseTableName2 = className2.toLowerCase();
		String classInstance2 = className2.toLowerCase().substring(0, className2.length()-1);
		String classInstanceFirstUpper2 = makeFirstLetterUpperCase(classInstance2);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("package application;\n\n");
		sb.append("import java.sql.Timestamp;\n\n");
		sb.append("public class "+className1+"Hybrid"+className2+" {\n\n\n");
		
		Field f_c[] = (contacts.getClass().getDeclaredFields());
		
		for (int i = 0; i < f_c.length; i++) {
			String varName = f_c[i].getName();
			String varType = f_c[i].getType().getSimpleName();
		

			sb.append("private "+varType+" "+varName+";\n");
		}
		
		sb.append("\n");
		Field f_l[] = (leads.getClass().getDeclaredFields());
		
		for (int i = 1; i < f_l.length; i++) {
			String varName = f_l[i].getName();
			String varType = f_l[i].getType().getSimpleName();

			sb.append("private "+varType+" "+varName+";\n");
		}
		
		
		

		
		
		System.out.println(sb.toString());
		
		for (int i = 0; i < f_c.length; i++) {
			String varName = f_c[i].getName();
			String varType = f_c[i].getType().getSimpleName();
			StringBuilder sb1 = new StringBuilder();
			char varChar[] = varName.toCharArray();
			// generate setter
			varChar[0] = Character.toUpperCase(varChar[0]);
			sb1.append("public void ");
			sb1.append("set");
			sb1.append(varChar);
			sb1.append("(");
			sb1.append(varType);
			sb1.append(" ");
			sb1.append(varName);
			sb1.append(")");
			sb1.append(" ");
			sb1.append("{");
			sb1.append("\n");
			sb1.append("\t");
			sb1.append("this.");
			sb1.append(varName);
			sb1.append(" = ");
			sb1.append(varName);
			sb1.append(";\n");
			sb1.append("}");

			System.out.println(sb1.toString());
			System.out.println();

			StringBuilder sb2 = new StringBuilder();
			sb2.append("public ");
			sb2.append(varType);
			sb2.append(" ");
			sb2.append("get");
			sb2.append(varChar);
			sb2.append("() {\n\t");
			sb2.append("return ");
			sb2.append(varName);
			sb2.append(";\n");
			sb2.append("}");

			System.out.println(sb2.toString());
			System.out.println();
		}
		
		for (int i = 1; i < f_l.length; i++) {
			String varName = f_l[i].getName();
			String varType = f_l[i].getType().getSimpleName();
			StringBuilder sb1 = new StringBuilder();
			char varChar[] = varName.toCharArray();
			// generate setter
			varChar[0] = Character.toUpperCase(varChar[0]);
			sb1.append("public void ");
			sb1.append("set");
			sb1.append(varChar);
			sb1.append("(");
			sb1.append(varType);
			sb1.append(" ");
			sb1.append(varName);
			sb1.append(")");
			sb1.append(" ");
			sb1.append("{");
			sb1.append("\n");
			sb1.append("\t");
			sb1.append("this.");
			sb1.append(varName);
			sb1.append(" = ");
			sb1.append(varName);
			sb1.append(";\n");
			sb1.append("}");

			System.out.println(sb1.toString());
			System.out.println();

			StringBuilder sb2 = new StringBuilder();
			sb2.append("public ");
			sb2.append(varType);
			sb2.append(" ");
			sb2.append("get");
			sb2.append(varChar);
			sb2.append("() {\n\t");
			sb2.append("return ");
			sb2.append(varName);
			sb2.append(";\n");
			sb2.append("}");

			System.out.println(sb2.toString());
			System.out.println();
		}
		
		System.out.println("}");
		
	}


	public void printDAOClassHeaderStructure(Object dataClassInstance) {
		String className = dataClassInstance.getClass().getSimpleName();
		StringBuilder sb = new StringBuilder();
		sb.append("package application;\n\n");
		sb.append("import java.sql.*;\n");
		sb.append("import java.util.ArrayList;\n\n");
		sb.append("public class ");
		sb.append(className+"DAO");
		sb.append(" {\n\n");
		sb.append("\tString URL = \""+MYSQL_URL+"\";\n");
		sb.append("\tString uname = \""+MYSQL_UNAME+"\";\n");
		sb.append("\tString pass = \""+MYSQL_PASS+"\";\n");
		sb.append("\n}");

		System.out.println(sb.toString());

	}

	public String makeFirstLetterUpperCase(String word) {
		char varChar[] = word.toCharArray();
		// generate setter
		varChar[0] = Character.toUpperCase(varChar[0]);
		String upperWord = String.valueOf(varChar);
		return upperWord;
	}

	public void printDAOClassGetAllDataIntoArrayList(Object dataClassInstance) {
		String className = dataClassInstance.getClass().getSimpleName();
		String databaseTableName = className.toLowerCase();
		String arrayListInstance = className.toLowerCase();
		String classInstance = className.toLowerCase().substring(0, className.length()-1);

		StringBuilder sb = new StringBuilder();

		sb.append("public ArrayList<" + className + "> getAll" + className + "() {\n\n");
		sb.append("ArrayList<"+className+"> "+arrayListInstance+" = new ArrayList<>();\n");
		sb.append("String query = \"SELECT * FROM "+databaseTableName+"\";\n\n");
		sb.append("Connection con;\n");
		sb.append("try {\n");
		sb.append("con = DriverManager.getConnection(URL, uname, pass);\n");
		sb.append("Statement st = con.createStatement();\n");
		sb.append("ResultSet rs = st.executeQuery(query);\n\n");
		sb.append("while(rs.next()) {\n");
		sb.append(className+" "+classInstance + " = new "+className+"();\n");

		Field f[] = (dataClassInstance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);

			sb.append(classInstance+".set"+upperVarName+"(rs.get"+upperVarType+"(\""+varName+"\"));\n");
		}
		sb.append(arrayListInstance+".add("+classInstance+");\n");
		sb.append("}\n\n");
		sb.append("return "+arrayListInstance+";\n");
		sb.append("} catch (SQLException e) {\n");
		sb.append("e.printStackTrace();\n");
		sb.append("return null;\n");
		sb.append("}\n\n");
		sb.append("}\n\n");


		System.out.println(sb.toString());

	}

	public void printDAOClassesGetSingleDataFromID(Object dataClassInstance) {
		String className = dataClassInstance.getClass().getSimpleName();
		String databaseTableName = className.toLowerCase();
		String classInstance = className.toLowerCase().substring(0, className.length()-1);
		String classInstanceFirstUpper = makeFirstLetterUpperCase(classInstance);

		StringBuilder sb = new StringBuilder();

		sb.append("public "+className+" get"+classInstanceFirstUpper+"FromID(int contact_id) {\n\n");
		sb.append("String query = \"SELECT * FROM "+databaseTableName+" WHERE contact_id = \" + contact_id;\n");
		sb.append("Connection con;\n\n");
		sb.append("try {\n");
		sb.append("con = DriverManager.getConnection(URL, uname, pass);\n");
		sb.append("Statement st = con.createStatement();\n");
		sb.append("ResultSet rs = st.executeQuery(query);\n\n");
		sb.append("rs.next();\n\n");
		sb.append(className+" "+classInstance+" = new "+className+"();\n");

		Field f[] = (dataClassInstance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);

			if (varType.equals("int")) {
				sb.append(classInstance+".set"+upperVarName+"(rs.get"+upperVarType+"(\""+varName+"\"));\n\n");
			} else if (varType.equals("Timestamp")) {
				sb.append("if (rs.get"+upperVarType+"(\""+varName+"\") != null){\n");
				sb.append("\t"+classInstance+".set"+upperVarName+"(rs.get"+upperVarType+"(\""+varName+"\"));\n");
				sb.append("} else {\n");
				sb.append("\t"+classInstance+".set"+upperVarName+"(null);\n");
				sb.append("}\n\n");
			} else {
				sb.append("if (rs.get"+upperVarType+"(\""+varName+"\") != null){\n");
				sb.append("\t"+classInstance+".set"+upperVarName+"(rs.get"+upperVarType+"(\""+varName+"\"));\n");
				sb.append("} else {\n");
				sb.append("\t"+classInstance+".set"+upperVarName+"(\"\");\n");
				sb.append("}\n\n");

			}
		}
		sb.append("\n\n");
		sb.append("return "+ classInstance+";\n\n");
		sb.append("} catch (SQLException e) {\n");
		sb.append("e.printStackTrace();\n");
		sb.append("return null;\n");
		sb.append("}\n\n");
		sb.append("}\n\n");



		System.out.println(sb.toString());

	}

	public void printDAOClassCheckDataEntryExistInDB(Object dataClassInstance,String fieldName) {

		String className = dataClassInstance.getClass().getSimpleName();
		String databaseTableName = className.toLowerCase();
		String arrayListInstance = className.toLowerCase();
		String classInstance = className.toLowerCase().substring(0, className.length()-1);
		String classInstanceFirstUpper = makeFirstLetterUpperCase(classInstance);
		StringBuilder sb = new StringBuilder();

		sb.append("public boolean check"+classInstanceFirstUpper+"Exist(");

		Field f[] = (dataClassInstance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);
			if (varName.equals(fieldName)) {

				sb.append(varType+" "+varName+") {\n\n");

				sb.append("String query = \"SELECT * FROM "+databaseTableName+"\";\n");
				sb.append("Connection con;\n\n");
				sb.append("try {\n");
				sb.append("con = DriverManager.getConnection(URL, uname, pass);\n");
				sb.append("Statement st = con.createStatement();\n");
				sb.append("ResultSet rs = st.executeQuery(query);\n\n");

				sb.append("while(rs.next()) {\n");
				if (upperVarType.equals("String")) {
				sb.append("\tif(rs.get"+upperVarType+"(\""+varName+Q+R_P+".equals("+varName+")) {\n");
				} else {
					sb.append("\tif(rs.get"+upperVarType+"(\""+varName+Q+R_P+" == ("+varName+")) {\n");	
				}
				sb.append("\t\treturn true;\n");
				sb.append("\t}\n");
				sb.append("}\n\n");
				sb.append("return false;\n\n");

				sb.append("} catch (SQLException e) {\n");
				sb.append("e.printStackTrace();\n");
				sb.append("return false;\n");
				sb.append("}\n\n");
				sb.append("}\n\n");

			} else {

			}
		}

		System.out.println(sb.toString());
	}

	public void printDAOClassDeleteDataEntryFromID(Object dataClassInstance) {

		String className = dataClassInstance.getClass().getSimpleName();
		String databaseTableName = className.toLowerCase();
		String classInstance = className.toLowerCase().substring(0, className.length()-1);
		String classInstanceFirstUpper = makeFirstLetterUpperCase(classInstance);

		StringBuilder sb = new StringBuilder();

		sb.append("public void"+" delete"+classInstanceFirstUpper+"FromID(int contact_id) {\n\n");
		sb.append("String query = \"DELETE FROM "+databaseTableName+" WHERE contact_id = \" + contact_id;\n");
		sb.append("Connection con;\n\n");
		sb.append("try {\n");
		sb.append("con = DriverManager.getConnection(URL, uname, pass);\n");
		sb.append("Statement st = con.createStatement();\n");
		sb.append("st.execute(query);\n\n");
		sb.append("st.close();\n");
		sb.append("con.close();\n\n");

		sb.append("} catch (SQLException e) {\n");
		sb.append("e.printStackTrace();\n");
		sb.append("}\n\n");
		sb.append("}\n\n");

		System.out.println(sb.toString());
	}

	public void printDAOClassAddNewDataEntry(Object dataClassInstance,String checkingExistFieldName,String mandatoryFieldsCommaSeparated) {

		String className = dataClassInstance.getClass().getSimpleName();
		String databaseTableName = className.toLowerCase();
		String classInstance = className.toLowerCase().substring(0, className.length()-1);
		String classInstanceFirstUpper = makeFirstLetterUpperCase(classInstance);

		StringBuilder sb = new StringBuilder();

		sb.append("public boolean addNew"+classInstanceFirstUpper+L_P);

		Field f[] = (dataClassInstance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);

			sb.append(varType +SPACE + varName + ",");


		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(R_P+SPACE+L_B+"\n\n");


		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);
			if (varName.equals(checkingExistFieldName)) {

				sb.append("if (check"+classInstanceFirstUpper+"Exist("+varName+")) {\n");
				sb.append("\treturn false;\n");
				sb.append("}\n\n");

			}	
		}

		sb.append("if (");
		String[] thelist = mandatoryFieldsCommaSeparated.split(",");

		for(String listItem:thelist) {
			sb.append(listItem+" == null || ");
		}

		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append(R_P+SPACE+L_B+"\n\n");
		sb.append("\treturn false;\n}\n\n");




		sb.append("String query = "+Q+"INSERT INTO "+databaseTableName+" VALUES (");

		if(databaseTableName.equals("contacts")) {
			sb.append("DEFAULT,");
			for (int i = 0; i < f.length-1; i++) {
				sb.append("?,");
			}


		} else {
			for (int i = 0; i < f.length; i++) {
				sb.append("?,");
			}
		}

		sb.deleteCharAt(sb.length()-1);
		sb.append(")"+Q+";\n\n");

		sb.append("Connection con;\n\n");
		sb.append("try {\n");
		sb.append("con = DriverManager.getConnection(URL, uname, pass);\n");
		sb.append("PreparedStatement st = con.prepareStatement(query);\n");

		if(databaseTableName.equals("contacts")) {
			for (int i = 1; i < f.length; i++) {
				String varName = f[i].getName();
				String varType = f[i].getType().getSimpleName();
				String upperVarType = makeFirstLetterUpperCase(varType);
				String upperVarName = makeFirstLetterUpperCase(varName);
				sb.append("st.set"+upperVarType+"("+i+", "+varName+");\n");
			}

		} else {
			for (int i = 0; i < f.length; i++) {
				String varName = f[i].getName();
				String varType = f[i].getType().getSimpleName();
				String upperVarType = makeFirstLetterUpperCase(varType);
				String upperVarName = makeFirstLetterUpperCase(varName);
				int i1 = i+1;
				sb.append("st.set"+upperVarType+"("+i1+", "+varName+");\n");
			}
		}

		sb.append("\nst.executeUpdate();\n");
		sb.append("return true;\n\n");

		sb.append("} catch (SQLException e) {\n");
		sb.append("e.printStackTrace();\n");
		sb.append("return false;\n");
		sb.append("}\n\n");
		sb.append("}\n\n");


		System.out.println(sb.toString());

	}

	public void printDAOClassModifyDataFromID(Object dataClassInstance,String mandatoryFieldsCommaSeparated) {

		String className = dataClassInstance.getClass().getSimpleName();
		String databaseTableName = className.toLowerCase();
		String classInstance = className.toLowerCase().substring(0, className.length()-1);
		String classInstanceFirstUpper = makeFirstLetterUpperCase(classInstance);

		StringBuilder sb = new StringBuilder();

		sb.append("public boolean modify"+classInstanceFirstUpper+"FromID(");

		Field f[] = (dataClassInstance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);

			sb.append(varType+" "+varName+",");
		}

		sb.deleteCharAt(sb.length()-1);
		sb.append(") {\n\n");
		
		sb.append("if (");

		String[] thelist = mandatoryFieldsCommaSeparated.split(",");

		for(String listItem:thelist) {
			sb.append(listItem+" == null || ");
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append(") {\n");
		sb.append("return false;\n}\n\n");
		
		sb.append("String query = "+Q+"UPDATE "+databaseTableName+" SET ");
		
		for (int i = 1; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);

			sb.append(varName+" = ? , ");
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append("WHERE "+f[0].getName()+" = "+Q+" + "+f[0].getName()+";\n\n");
		
		
		sb.append("Connection con;\n\n");
		sb.append("try {\n");
		sb.append("con = DriverManager.getConnection(URL, uname, pass);\n");
		sb.append("PreparedStatement st = con.prepareStatement(query);\n\n");
		
		for (int i = 1; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);

			sb.append("st.set"+upperVarType+"("+i+", "+varName+");\n");
		}
		
		sb.append("\nst.executeUpdate();\n");
		sb.append("return true;\n\n");
		
		sb.append("} catch (SQLException e) {\n");
		sb.append("e.printStackTrace();\n");
		sb.append("return false;\n");
		sb.append("}\n\n");
		sb.append("}\n\n");

		System.out.println(sb.toString());
	}

	public void printDAOClassGetAllHybridData(Object dataClassInstanceHybrid,Object dataClassInstance1,Object dataClassInstance2) {
		// TODO Auto-generated method stub
		String className = dataClassInstanceHybrid.getClass().getSimpleName();
		String classInstance = className.toLowerCase().substring(0, className.length()-1);
		String arrayListInstance = className.toLowerCase();
	
		String databaseTableName1 = dataClassInstance1.getClass().getSimpleName().toLowerCase();
		String databaseTableName2 = dataClassInstance2.getClass().getSimpleName().toLowerCase();

		StringBuilder sb = new StringBuilder();

		sb.append("public ArrayList<" + className + "> getAll" + className + "() {\n\n");
		sb.append("ArrayList<"+className+"> "+arrayListInstance+" = new ArrayList<>();\n");
		sb.append("String query = \"SELECT * FROM "+databaseTableName1+" a JOIN "+databaseTableName2+" b USING (contact_id);\n\n");
		sb.append("Connection con;\n");
		sb.append("try {\n");
		sb.append("con = DriverManager.getConnection(URL, uname, pass);\n");
		sb.append("Statement st = con.createStatement();\n");
		sb.append("ResultSet rs = st.executeQuery(query);\n\n");
		sb.append("while(rs.next()) {\n");
		sb.append(className+" "+classInstance + " = new "+className+"();\n");

		Field f[] = (dataClassInstanceHybrid.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);

			sb.append(classInstance+".set"+upperVarName+"(rs.get"+upperVarType+"(\""+varName+"\"));\n");
		}
		sb.append(arrayListInstance+".add("+classInstance+");\n");
		sb.append("}\n\n");
		sb.append("return "+arrayListInstance+";\n");
		sb.append("} catch (SQLException e) {\n");
		sb.append("e.printStackTrace();\n");
		sb.append("return null;\n");
		sb.append("}\n\n");
		sb.append("}\n\n");


		System.out.println(sb.toString());
		
	}

	public void printHomeControllerInitialize(Object dataClassInstance,Object DAOinstance) {
		String className = dataClassInstance.getClass().getSimpleName();
		String DAOname = DAOinstance.getClass().getSimpleName();
		
		StringBuilder inst = new StringBuilder();
		inst.append(Character.toLowerCase(className.toCharArray()[0]));
		char[] classNameChar = className.toCharArray();
		for(int i=1;i<classNameChar.length;i++) {
			inst.append(classNameChar[i]);
		}
		String classInstanceName = inst.toString();
		
		StringBuilder inst2 = new StringBuilder();
		inst2.append(Character.toLowerCase(DAOname.toCharArray()[0]));
		char[] classNameChar1 = DAOname.toCharArray();
		for(int i=1;i<classNameChar1.length;i++) {
			inst2.append(classNameChar1[i]);
		}
		String DAOInstanceName = inst2.toString();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("private SceneManager sceneManager = new SceneManager();\n\n");
		sb.append("private ArrayList<"+className+"> "+classInstanceName+"Array;\n");
		sb.append("private ObservableList<"+className+"> "+classInstanceName+"Observe;\n");
		sb.append("private ObservableList<"+className+"> selected"+className+";\n\n");
		
		sb.append("private TempDataDAO tempDataDAO = new TempDataDAO();\n\n");
		
		sb.append("@Override\n");
		sb.append("public void initialize(URL arg0, ResourceBundle arg1) {\n\n");
		
		sb.append(DAOname+" "+DAOInstanceName+" = new "+DAOname+"();\n");
		sb.append(classInstanceName+"Array = new ArrayList<>();\n");
		sb.append(classInstanceName+"Array = "+DAOInstanceName+".getAll"+className+"();\n");
		sb.append(classInstanceName+"Observe = FXCollections.observableArrayList("+classInstanceName+"Array);\n\n");
		
		Field f[] = (dataClassInstance.getClass().getDeclaredFields());

		for (int i = 0; i < f.length; i++) {
			String varName = f[i].getName();
			String varType = f[i].getType().getSimpleName();
			String upperVarType = makeFirstLetterUpperCase(varType);
			String upperVarName = makeFirstLetterUpperCase(varName);
			
			sb.append("this."+varName+"_c.setCellValueFactory((new PropertyValueFactory<");
			sb.append(className);
			sb.append(",");
			if (upperVarType.equals("Int")) {
				upperVarType = "Integer";
			}
			sb.append(upperVarType);
			sb.append(">("+Q+varName+Q+")));\n");
		}
		sb.append("\n");
		sb.append("this.table_view.setItems("+classInstanceName+"Observe"+");\n\n");
		
		sb.append("selected"+className+" = table_view.getSelectionModel().getSelectedItems();\n\n");
		
		sb.append("selected"+className+".addListener(new ListChangeListener<Object>() {\n\n");
		sb.append("@Override\n");
		sb.append("public void onChanged(Change<? extends Object> arg0) {\n\n");
		sb.append("}\n\n});\n\n}\n");
		
		
		System.out.println(sb.toString());
		
	}
	
	

	
}
