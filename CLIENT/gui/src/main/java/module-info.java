module gui{
	requires logic;
	requires SimpleNet;
	requires javafx.controls;
	requires vlcj;
	requires jna;
	requires java.desktop;
	opens com.advertstv.clientui to javafx.graphics;
}
