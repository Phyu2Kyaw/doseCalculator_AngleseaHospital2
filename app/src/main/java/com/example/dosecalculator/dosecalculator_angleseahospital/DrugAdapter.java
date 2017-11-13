package com.example.dosecalculator.dosecalculator_angleseahospital;

/*public class DrugAdapter extends ArrayList<Drugs> {

    private LayoutInflater myInflater;
    private ArrayList<Drugs> drugs;
    private int mViewResourceId;


    public DrugAdapter(Context context, int resource, ArrayList<Drugs> drugs) {
        super(context, resource, drugs);
        this.drugs = drugs;
        myInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId= resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = myInflater.inflate(mViewResourceId,null);
        Drugs drug = drugs.get(position);

        if(drug != null){
            TextView drugName =(TextView) convertView.findViewById(R.id.lbl_result_drug_name);
            TextView weight = (TextView) convertView.findViewById(R.id.lbl_result_weight_mg);
            TextView volume = (TextView) convertView.findViewById(R.id.lbl_result_volume_mL);
            TextView maxDosage = (TextView) convertView.findViewById(R.id.lbl_result_max_Dosage);
            TextView calcMethod = (TextView) convertView.findViewById(R.id.lbl_result_calc_method);
            TextView typePatient = (TextView) convertView.findViewById(R.id.lbl_result_type_patient);

            if(drugName != null){
                drugName.setText((drug.getDrugName()));
            }
            if(weight != null){
                weight.setText((drug.getDrugWeight()));
            }
            if(volume != null){
                volume.setText((drug.getDrugVolume()));
            }
            if(maxDosage != null){
                maxDosage.setText((drug.getMaxDosage()));
            }
            if(calcMethod != null){
                calcMethod.setText((drug.getCalcMethod()));
            }
            if(typePatient != null){
                typePatient.setText((drug.getTypePatient()));
            }

        }
        return convertView;

    }
}
*/