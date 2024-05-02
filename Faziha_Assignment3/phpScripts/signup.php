<?php
header('Content-Type: application/json');
include 'conn.php';

$response = array();

$requiredFields = array('name', 'email', 'number', 'country', 'city', 'password');

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $inputJSON = file_get_contents('php://input');
    $input = json_decode($inputJSON, true);

    $missingFields = array();
    foreach ($requiredFields as $field) {
        if (!isset($input[$field]) || empty($input[$field])) {
            $missingFields[] = $field;
        }
    }

    if (!empty($missingFields)) {
        $response['Status'] = 0;
        $response['Message'] = "Required fields missing: " . implode(', ', $missingFields);
    } else {
        $name = $input['name'];
        $email = $input['email'];
        $contact_no = $input['number'];
        $city = $input['city'];
        $country = $input['country'];
        $password = $input['password'];

        $sql = "INSERT INTO `users`(`name`, `email`, `number`, `country`, `city`, `password` ) 
                VALUES ('$name','$email','$contact_no','$country','$city','$password')";

        if (mysqli_query($conn, $sql)) {
            $response['Status'] = 1;
            $response['id'] = mysqli_insert_id($conn);
            $response['Message'] = "Data inserted successfully";
        } else {
            $response['Status'] = 0;
            $response['Message'] = "Data insertion failed";
        }
    }
} else {
    $response['Status'] = 0;
    $response['Message'] = "No data received";
}

echo json_encode($response);

?>