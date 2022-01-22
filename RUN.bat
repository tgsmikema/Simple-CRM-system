for /f "delims=" %%A in ('type pathOfJavaFXLibrary.txt') do set "var=%%A"
set aaa=java --module-path "
set bbb=" --add-modules javafx.controls,javafx.fxml -jar crm.jar
set finalvar=%aaa%%var%%bbb%
%finalvar%
EXIT