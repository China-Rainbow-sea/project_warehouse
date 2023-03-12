package com.bjpowernode.client.login;



import com.bjpowernode.client.MainLauncher;
import com.bjpowernode.client.chat.ChatController;
import com.bjpowernode.client.chat.Listener;
import com.bjpowernode.client.util.Drag;
import com.bjpowernode.client.util.ThreadPoolUtil;
import com.trynotifications.util.ResizeHelper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private ImageView defaultView;
    @FXML private ImageView girlView;
    @FXML private ImageView boyView;
    @FXML public  TextField hostnameTextfield;
    @FXML private TextField portTextfield;
    @FXML private TextField usernameTextfield;
    @FXML private ChoiceBox imagePicker;
    @FXML private Label selectedPicture;
    @FXML private BorderPane borderPane;
    public static ChatController chatController;
    private Scene scene;

    private static LoginController instance;

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    /**
     *  处理点击登录按钮事件
     * @throws IOException
     */
    public void loginButtonAction() throws IOException {
        String hostname = hostnameTextfield.getText();
        int port = Integer.parseInt(portTextfield.getText());
        String username = usernameTextfield.getText();
        String picture = selectedPicture.getText();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ChatView.fxml"));
        Parent window = (Pane) fxmlLoader.load();
        chatController = fxmlLoader.getController();

        //创建监听器对象
        Listener listener = new Listener(hostname, port, username, picture,chatController);
        //将监听器加入到线程池
        ThreadPoolUtil.poolExecutor.execute(listener);

        this.scene = new Scene(window);
    }

    /**
     *  显示界面场景
     */
    public void showScene() {
        Platform.runLater(() -> {
            Stage stage = (Stage) hostnameTextfield.getScene().getWindow();
            stage.setResizable(true);
            stage.setWidth(1040);
            stage.setHeight(620);

            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.setScene(this.scene);
            stage.setMinWidth(800);
            stage.setMinHeight(300);
            ResizeHelper.addResizeListener(stage);
            stage.centerOnScreen();
            chatController.setUsernameLabel(usernameTextfield.getText());
            chatController.setImageLabel(selectedPicture.getText());
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagePicker.getSelectionModel().selectFirst();
        selectedPicture.textProperty().bind(imagePicker.getSelectionModel().selectedItemProperty());
        selectedPicture.setVisible(false);

        //处理鼠标拖拽界面
        new Drag().handleDrag(borderPane);

        //处理头像
        imagePicker.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (selected, oldPicture, newPicture) -> {
            if (!oldPicture.equals(newPicture)) {
                //隐藏所有头像
                defaultView.setVisible(false);
                boyView.setVisible(false);
                girlView.setVisible(false);

                //展示用户选中的头像
                switch (newPicture) {
                    case "default":
                        defaultView.setVisible(true);
                        break;
                    case "boy":
                        boyView.setVisible(true);
                        break;
                    case "girl":
                        girlView.setVisible(true);
                        break;
                }
            }
        });
        int numberOfSquares = 30;
        while (numberOfSquares > 0){
            generateAnimation();
            numberOfSquares--;
        }
    }


    /**
     * 生成随机动画
     */
    public void generateAnimation(){
        Random rand = new Random();
        int size = rand.nextInt(50) + 1;
        int speed = rand.nextInt(10) + 5;
        int startXPoint = rand.nextInt(420);
        int startYPoint = rand.nextInt(350);
        int direction = rand.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        Rectangle r1 = null;

        switch (direction){
            case 1 :
                // MOVE LEFT TO RIGHT
                r1 = new Rectangle(0,startYPoint,size,size);
                moveXAxis = new KeyValue(r1.xProperty(), 350 -  size);
                break;
            case 2 :
                // MOVE TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,size,size);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - size);
                break;
            case 3 :
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,size,size);
                moveXAxis = new KeyValue(r1.xProperty(), 350 -  size);
                moveYAxis = new KeyValue(r1.yProperty(), 420 - size);
                break;
            case 4 :
                // MOVE BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,420-size ,size,size);
                moveYAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 5 :
                // MOVE RIGHT TO LEFT
                r1 = new Rectangle(420-size,startYPoint,size,size);
                moveXAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 6 :
                //MOVE RIGHT TO LEFT, BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,0,size,size);
                moveXAxis = new KeyValue(r1.yProperty(), 420 - size);
                moveYAxis = new KeyValue(r1.xProperty(), 350 -  size);
                break;
        }

        r1.setFill(Color.web("#F89406"));
        r1.setOpacity(0.1);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speed * 1000), moveXAxis, moveYAxis);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        borderPane.getChildren().add(borderPane.getChildren().size()-1,r1);
    }

    /**
     * 关闭
     */
    public void closeSystem(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * 最小化
     */
    public void minimizeWindow(){
        MainLauncher.getPrimaryStage().setIconified(true);
    }


}
