module gui{
	requires logic;
	requires SimpleNet;
	requires javafx.controls;
	opens com.advertstv.clientui to javafx.graphics;
}
