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
				sb.append("\tif(rs.get"+upperVarType+"(\""+varName+Q+R_P+".equals("+varName+")) {\n");
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
	
	
	

}
