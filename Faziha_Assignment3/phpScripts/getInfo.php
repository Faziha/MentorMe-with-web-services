<?php
include 'conn.php';

$response = array();

if (isset($_GET['id'])) {
    $id = mysqli_real_escape_string($conn, $_GET['id']);

    $sql = "SELECT * FROM `users` WHERE id = '$id'";
    $result = mysqli_query($conn, $sql);

    if ($result) {
        if (mysqli_num_rows($result) > 0) {
            $response['Status'] = 1;
            $response['user'] = mysqli_fetch_assoc($result);
        } else {
            $response['Status'] = 0;
            $response['Message'] = "User not found";
        }
    } else {
        $response['Status'] = 0;
        $response['Message'] = "Error fetching user data: " . mysqli_error($conn);
    }
} else {
    $response['Status'] = 0;
    $response['Message'] = "ID parameter missing";
}

// Send JSON response
echo json_encode($response);
?>
