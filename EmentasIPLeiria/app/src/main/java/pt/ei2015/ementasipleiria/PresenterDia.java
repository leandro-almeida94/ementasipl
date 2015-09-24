package pt.ei2015.ementasipleiria;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PresenterDia.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PresenterDia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresenterDia extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment segunda.
     */

    Dia dia;
    Boolean completo;
    boolean isSopa;

    TextView almocoCarne;
    TextView almocoPeixe;
    TextView almocoVegetariano;
    TextView almocoSopa;
    TextView almocoSobremesa;

    TextView jantarCarne;
    TextView jantarPeixe;
    TextView jantarSopa;
    TextView jantarSobremesa;

    //labels

    TextView labelAlmoco;
    TextView labelAlmocoPratoCarne;
    TextView labelAlmocoPratoPeixe;
    TextView labelAlmocoPratoVegetariano;
    TextView labelAlmocoSopa;
    TextView labelAlmocoSobremesa;

    TextView labelJantar;
    TextView labelJantarPratoCarne;
    TextView labelJantarPratoPeixe;
    TextView labelJantarSopa;
    TextView labelJantarSobremesa;

    public void setDia(Dia dia, boolean completo, Boolean isSopa) {
        this.isSopa=isSopa;
        this.completo=completo;
        this.dia = dia;
        setInfo();
   /*     if(t!=null) {
            if (completo)
                //  textView.setText(textView.getText() + "\n\n" + x.dia.toString() + " - almoço: " + x.getAlmoco().getCarne() + " - " + x.getAlmoco().getPeixe() + " - " + x.getAlmoco().getVegetariano());
                t.setText("\n\n" + dia.dia.toString() + " - almoço: " + dia.getAlmoco().getCarne() + " - " + dia.getAlmoco().getPeixe() + " - " + dia.getAlmoco().getVegetariano());

            else
                // textView.setText(textView.getText() + "\n\n" + x.dia.toString() + " - prato: " + x.getPrato());
                t.setText("\n\n" + dia.dia.toString() + " - prato: " + dia.getPrato());
        }*/
    }

    // TODO: Rename and change types and number of parameters
    public static PresenterDia newInstance(String param1, String param2) {
        PresenterDia fragment = new PresenterDia();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PresenterDia() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_presenter_dia, container, false);
        almocoCarne = (TextView)layout.findViewById(R.id.txtAlmocoPratoCarne);
        almocoPeixe = (TextView)layout.findViewById(R.id.txtAlmocoPratoPeixe);
        almocoVegetariano = (TextView)layout.findViewById(R.id.txtAlmocoPratoVegetariano);
        almocoSopa = (TextView)layout.findViewById(R.id.txtAlmocoSopa);
        almocoSobremesa = (TextView)layout.findViewById(R.id.txtAlmocoSobremesa);

        jantarCarne = (TextView)layout.findViewById(R.id.txtJantarPratoCarne);
        jantarPeixe = (TextView)layout.findViewById(R.id.txtJantarPratoPeixe);
        jantarSopa = (TextView)layout.findViewById(R.id.txtJantarSopa);
        jantarSobremesa = (TextView)layout.findViewById(R.id.txtJantarSobremesa);

        //labels
        labelAlmoco = (TextView) layout.findViewById(R.id.labelAlmoco);
        labelAlmocoPratoCarne = (TextView) layout.findViewById(R.id.labelAlmocoPratoCarne);
        labelAlmocoPratoPeixe = (TextView) layout.findViewById(R.id.labelAlmocoPratoPeixe);
        labelAlmocoPratoVegetariano = (TextView) layout.findViewById(R.id.labelAlmocoPratoVegetariano);
        labelAlmocoSopa = (TextView) layout.findViewById(R.id.labelAlmocoSopa);
        labelAlmocoSobremesa = (TextView) layout.findViewById(R.id.labelAlmocoSobremesa);

        labelJantar = (TextView) layout.findViewById(R.id.labelJantar);
        labelJantarPratoCarne = (TextView) layout.findViewById(R.id.labelJantarPratoCarne);
        labelJantarPratoPeixe= (TextView) layout.findViewById(R.id.labelJantarPratoPeixe);
        labelJantarSopa = (TextView) layout.findViewById(R.id.labelJantarSopa);
        labelJantarSobremesa = (TextView) layout.findViewById(R.id.labelJantarSobremesa);

      //  t.setText(dia);
        setInfo();
        return layout;
    }

    private void setInfo() {
        if(dia!=null && jantarSobremesa!=null) {
            showAll(completo);
            if (completo) {
                //  textView.setText(textView.getText() + "\n\n" + x.dia.toString() + " - almoço: " + x.getAlmoco().getCarne() + " - " + x.getAlmoco().getPeixe() + " - " + x.getAlmoco().getVegetariano());
                //t.setText("\n\n" + dia.dia.toString() + " - almoço: " + dia.getAlmoco().getCarne() + " - " + dia.getAlmoco().getPeixe() + " - " + dia.getAlmoco().getVegetariano());
                almocoCarne.setText(dia.getAlmoco().getCarne());
                almocoPeixe.setText(dia.getAlmoco().getPeixe());
                almocoVegetariano.setText(dia.getAlmoco().getVegetariano());
                almocoSopa.setText(dia.getAlmoco().getSopa());
                almocoSobremesa.setText(dia.getAlmoco().getSobremesa());

                jantarCarne.setText(dia.getJantar().getCarne());
                jantarPeixe.setText(dia.getJantar().getPeixe());
                jantarSopa.setText(dia.getJantar().getSopa());
                jantarSobremesa.setText(dia.getJantar().getSobremesa());
            } else {
                // textView.setText(textView.getText() + "\n\n" + x.dia.toString() + " - prato: " + x.getPrato());
                almocoCarne.setText(dia.getPrato());
            }
        }
    }

    private void showAll(Boolean completo) {
     if(completo){
         labelAlmocoPratoCarne.setText("Prato de carne");

         //labelAlmoco.setVisibility(View.VISIBLE);
         labelAlmocoPratoPeixe.setVisibility(View.VISIBLE);
         labelAlmocoPratoVegetariano.setVisibility(View.VISIBLE);
         labelAlmocoSopa.setVisibility(View.VISIBLE);
         labelAlmocoSobremesa.setVisibility(View.VISIBLE);

         labelJantar.setVisibility(View.VISIBLE);
         labelJantarPratoCarne.setVisibility(View.VISIBLE);
         labelJantarPratoPeixe.setVisibility(View.VISIBLE);
         labelJantarSopa.setVisibility(View.VISIBLE);
         labelJantarSobremesa.setVisibility(View.VISIBLE);
     }else {
         if(isSopa)
             labelAlmocoPratoCarne.setText("Sopa");
         else
            labelAlmocoPratoCarne.setText("Refeição");

         //labelAlmoco.setVisibility(View.GONE);
         labelAlmocoPratoPeixe.setVisibility(View.GONE);
         labelAlmocoPratoVegetariano.setVisibility(View.GONE);
         labelAlmocoSopa.setVisibility(View.GONE);
         labelAlmocoSobremesa.setVisibility(View.GONE);

         labelJantar.setVisibility(View.GONE);
         labelJantarPratoCarne.setVisibility(View.GONE);
         labelJantarPratoPeixe.setVisibility(View.GONE);
         labelJantarSopa.setVisibility(View.GONE);
         labelJantarSobremesa.setVisibility(View.GONE);
     }
    }

    public void clear(){
        if(almocoCarne!=null && almocoPeixe!=null && almocoVegetariano!=null && almocoSopa!=null &&almocoSobremesa!=null
                &&jantarCarne!=null &&jantarPeixe!=null &&jantarSopa!=null &&jantarSobremesa!=null ) {
            almocoCarne.setText("");
            almocoPeixe.setText("");
            almocoVegetariano.setText("");
            almocoSopa.setText("");
            almocoSobremesa.setText("");

            jantarCarne.setText("");
            jantarPeixe.setText("");
            jantarSopa.setText("");
            jantarSobremesa.setText("");
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
