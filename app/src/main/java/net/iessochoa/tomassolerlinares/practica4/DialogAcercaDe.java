package net.iessochoa.tomassolerlinares.practica4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

/**
 * Clase encargada de mostrar el diálogo Acerca De
 */
public class DialogAcercaDe extends DialogFragment {
    /**
     * Método encargado de mostrar el diálogo Acerca De
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.AcercaDeTexto)
                .setTitle(R.string.AcercaDeTitulo)
                .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}