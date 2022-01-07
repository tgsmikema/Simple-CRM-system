package application;

import java.lang.reflect.Field;

public class PrintingFunctionModuleManagement {
	
	private String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/super_chat_pal_crm";
	private String MYSQL_UNAME = "root";
	private String MYSQL_PASS = "masiqi93";

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
	

}
