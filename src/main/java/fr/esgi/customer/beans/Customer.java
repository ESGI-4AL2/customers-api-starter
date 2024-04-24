package fr.esgi.customer.beans;

import org.apache.commons.csv.CSVRecord;

public record Customer(int referenceId,
                       String companyName,
                       String town,
                       String address,
                       String siret
) {

    public static Customer create(CSVRecord csvRecord, String[] headers) {
        return new Customer(
                Integer.parseInt(csvRecord.get(headers[0])),
                csvRecord.get(headers[1]),
                csvRecord.get(headers[2]),
                csvRecord.get(headers[3]),
                csvRecord.get(headers[4]));
    }

}
