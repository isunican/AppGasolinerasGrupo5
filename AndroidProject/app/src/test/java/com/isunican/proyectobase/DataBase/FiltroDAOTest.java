package com.isunican.proyectobase.DataBase;

//@RunWith(RobolectricTestRunner.class)
//@Config(sdk = Build.VERSION_CODES.O_MR1)
public class FiltroDAOTest {

    private Filtro filtroPrueba;
    private FiltroDAO sut;
/*
    @Before
    public void setUp() {
        filtroPrueba=new Filtro("Gas", false, true);
        sut = new FiltroDAO(RuntimeEnvironment.application.getApplicationContext());

    }
*/
    /**
     * Comprobamos si la configuracion de filtros anhadido se encuentra en la base de datos
     */
    /*
    @Test
    public void filtroAnhadidoTest() {
        sut.addFiltro(filtroPrueba);
        assertEquals(sut.getFiltro("Gas").getNombre(),filtroPrueba.getNombre());
    }
     */
}
