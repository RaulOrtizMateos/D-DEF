package mx.itesm.seb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class PantaSettings implements Screen {
    //Variables iniciales
    private final Juego juego;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;

    //Referencias de texturas
    private Texture texturaFondo;
    private Text titleHead;

    //Escena: HUD
    private Stage escenaHUD;


    public PantaSettings(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearTexto();
        crearHUD();

    }

    private void crearTexto() {
        titleHead = new Text("Settings");
    }

    private void crearHUD() {
        escenaHUD = new Stage(view);
        ImageButton btnBack = configurarBotonBack();
        agregarBotones(btnBack);
        Gdx.input.setInputProcessor(escenaHUD);
    }

    private void agregarBotones(ImageButton btnBack) {
        escenaHUD.addActor(btnBack);
    }

    private ImageButton configurarBotonBack() {
        //Bot
//Da
        //Botón Back
        TextureRegionDrawable trdBack = new TextureRegionDrawable(new TextureRegion(new Texture("back.png")));
        TextureRegionDrawable trdBackPressed = new TextureRegionDrawable(new TextureRegion(new Texture("backPressed.png")));
        ImageButton btnBack = new ImageButton(trdBack, trdBackPressed);
        btnBack.setPosition(0, Juego.ALTO - btnBack.getHeight());
        //Evento de Botón Back
        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaMenu(juego));
            }
        });
        return btnBack;
    }
//y
    private void cargarTexturas() {
        texturaFondo = new Texture("D-DEF/oceanBackgroundPlayVertical.png");
    }

    private void configurarVista(){
        configurarCamara();
        view = new StretchViewport(Juego.ANCHO, Juego.ALTO, camera);
        batch = new SpriteBatch();
    }

    private void configurarCamara() {
        camera = new OrthographicCamera();
        camera.position.set(Juego.ANCHO/2, Juego.ALTO/2, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {


        borrarPantalla();
        batch.begin();

        batch.draw(texturaFondo,1,1);
        titleHead.draw(batch,juego.ANCHO/2, juego.ALTO-80);
        batch.end();

        escenaHUD.draw();
    }



    private void borrarPantalla() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        dibujarElementos();
        escenaHUD.draw();
    }

    private void dibujarElementos() {
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        dibujarFondo();
        batch.end();
    }

    private void dibujarFondo() {
        //Dibujo de Fondo
        batch.draw(texturaFondo, 0, 0);
    }

    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        //dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
    }


    class ProcesadorEntrada implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;

        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
