<?php
header('Content-Type: application/json');
include 'conn.php';

$response = array();
$requiredFields = array('email', 'password');


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
        $response['id'] = -1;
    } 
    else
    {
        $email = $input['email'];
        $password = $input['password'];

        $userId = authenticateUser($email, $password);
        if ($userId != -1) {
            $response['Status'] = 1;
            $response['Message'] = "Login successful";
            $response['UserID'] = $userId; 
        } 
       
        else {
            $response['Status'] = 0;
            $response['Message'] = "Login failed";
            $response['id'] = -1;
        }
} 
}
echo json_encode($response);

function authenticateUser($email, $password) {
    global $conn;
    $sql = "SELECT * FROM `users` WHERE `email` = '$email' AND `password` = '$password'";
    $result = mysqli_query($conn, $sql);

    if (mysqli_num_rows($result) > 0) {
        $row = mysqli_fetch_assoc($result);
        return $row['id']; 
    } else {
        return -1;
    }
}
?>
