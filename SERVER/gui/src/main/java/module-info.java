module gui{
	requires logic;
	requires SimpleNet;
	requires javafx.controls;
	opens com.advertstv.serverui to javafx.graphics;
}
