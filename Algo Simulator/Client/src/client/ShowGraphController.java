package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShowGraphController {

    @FXML
    public Button showButton;

    public ShowGraphController(){
    }
    @FXML
    public AnchorPane GraphShow;
    private int i=0,j=0,l=0;
    private double[] edges1 = new double[5050];
    private double[] edges2 = new double[5050];
    private Circle[] circle = new Circle[100];
    private Text[] text = new Text[100];
    private Line[] line = new Line[5050];
    private int nodes=0,edges = 0;
    private int[][] adj=new int[5050][5050];

    @FXML
    public void loadnewGraph() throws FileNotFoundException {
        //reading value of i from the file
        File file = new File("D:\\Study\\L-1 T-2\\Algo Simulator\\Client\\I.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            nodes = scanner.nextInt();
        }
        //reading value of j from the file
        File jfile = new File("D:\\Study\\L-1 T-2\\Algo Simulator\\Client\\J.txt");
        scanner = new Scanner(jfile);
        while (scanner.hasNextInt()) {
            edges = scanner.nextInt();
        }
        //reading centers of circle from the file
        File fileCenter = new File("D:\\Study\\L-1 T-2\\Algo Simulator\\Client\\Circle Center.txt");
        Scanner scannerCenter = new Scanner(fileCenter);
        String[] strings = new String[200];
        double[] centers = new double[200];
        int stringLength = 0;
        while (scannerCenter.hasNext()) {
            String sampleString = scannerCenter.nextLine();
            strings = sampleString.split("\\s+");
            stringLength = strings.length;
            for (l=0; l < stringLength; l++) {
                String numString = strings[l];
                centers[l] = Double.parseDouble(numString);
            }
            for (i = 0; i < nodes; ) {
                for (l = 0; l < stringLength-2; l += 2) {
                    circle[i] = new Circle();
                    text[i] = new Text();
                    circle[i].setCenterX(centers[l]);
                    circle[i].setCenterY(centers[l + 1]);
                    circle[i].setRadius(20.0f);
                    circle[i].setFill(Color.rgb(200, 150, 180));
                    text[i].setX(centers[l] - 20);
                    text[i].setY(centers[l + 1] - 30);
                    String s = Integer.toString(i);
                    s = "Node " + s;
                    text[i].setText(s);
                    GraphShow.getChildren().addAll(circle[i],text[i]);
                    //GraphShow.getChildren().addAll(circle[i], text[i]);
                    i++;
                }
            }
        }
        //reading edge1 from file
        File fileEdge1 = new File("D:\\Study\\L-1 T-2\\Algo Simulator\\Client\\Edge1.txt");
        Scanner edgeScanneer = new Scanner(fileEdge1);
        String[] edgeStrings = new String[200];
        while(edgeScanneer.hasNext()) {
            String sampleString = edgeScanneer.nextLine();
            edgeStrings = sampleString.split("\\s+");
            stringLength = edgeStrings.length;
            for (l = 0; l < stringLength; l++) {
                String numString = edgeStrings[l];
                edges1[l] = Double.parseDouble(numString);
            }
        }
        //reading edge2 coordinates from file
        File fileEdge2 = new File("D:\\Study\\L-1 T-2\\Algo Simulator\\Client\\Edge2.txt");
        Scanner edge2Scanneer = new Scanner(fileEdge2);
        String[] edge2Strings = new String[200];
        while(edge2Scanneer.hasNext()){
            String sampleString = edge2Scanneer.nextLine();
            edge2Strings = sampleString.split("\\s+");
            stringLength = edge2Strings.length;
            for (l=0; l < stringLength; l++) {
                String numString = edge2Strings[l];
                edges2[l] = Double.parseDouble(numString);
            }
        }
        for (j=0;j<edges;){
            for(l=0;l<stringLength;l+=2){
                line[j] = new Line();
                line[j].setStartX(edges1[l]);
                line[j].setStartY(edges1[l+1]);
                line[j].setEndX(edges2[l]);
                line[j].setEndY(edges2[l+1]);
                line[j].setStrokeWidth(2);
                line[j].setStroke(Color.rgb(200, 150, 180));
                line[j].setFill(Color.rgb(200, 150, 180));
                GraphShow.getChildren().addAll(line[j]);
                j++;
            }
        }
        File fileadj = new File("D:\\Study\\L-1 T-2\\Algo Simulator\\Client\\Adjacency.txt");
        Scanner adjScanner = new Scanner(fileadj);
        String[] adjStrings = new String[200];
        while(adjScanner.hasNext()){
            String sampleString = adjScanner.nextLine();
            //System.out.println(sampleString);
            adjStrings = sampleString.split("\\s+");
            stringLength = adjStrings.length;
            int n=0,m=0;
            for (l=0; l < stringLength; l++) {
                String numString = adjStrings[l];
                adj[n][m] = Integer.parseInt(numString);
                //System.out.println(adj[n][m]);
                m++;
                if(m>=Math.sqrt(stringLength)){
                    m=0;n++;
                }
            }
        }
    }
}
