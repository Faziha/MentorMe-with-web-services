<?php
header('Content-Type: application/json');
include 'conn.php';

$response = array();

// Check if the request method is POST
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get the input JSON data
    $inputJSON = file_get_contents('php://input');
    $input = json_decode($inputJSON, true);

    // Check if required fields are present
    $requiredFields = array('id', 'name', 'email', 'number', 'city', 'country');
    $missingFields = array_diff($requiredFields, array_keys($input));
    if (!empty($missingFields)) {
        $response['Status'] = 0;
        $response['Message'] = "Required fields missing: " . implode(', ', $missingFields);
    } else {
        // Extract input data
        $userId = $input['id'];
        $name = $input['name'];
        $email = $input['email'];
        $number = $input['number'];
        $city = $input['city'];
        $country = $input['country'];

        // Update user profile in the database
        $sql = "UPDATE `users` SET `name` = '$name', `email` = '$email', `number` = '$number', `city` = '$city', `country` = '$country' WHERE `id` = $userId";
        if (mysqli_query($conn, $sql)) {
            $response['Status'] = 1;
            $response['Message'] = "Profile updated successfully";
        } else {
            $response['Status'] = 0;
            $response['Message'] = "Error updating profile: " . mysqli_error($conn);
        }
    }
} else {
    $response['Status'] = 0;
    $response['Message'] = "Invalid request method";
}

echo json_encode($response);
?>
