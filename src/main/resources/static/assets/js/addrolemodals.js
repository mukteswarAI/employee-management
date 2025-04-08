
let originalFormData = {};

function showEditDepartmentModal(deptId, deptName, deptDescription) {
	// Store original values
	originalFormData = {
		departmentId: deptId,
		departmentName: deptName,
		description: deptDescription
	};

	// Populate the form
	$('#editDepartmentId').val(deptId);
	$('#editDepartmentName').val(deptName);
	$('#editDepartmentDescription').val(deptDescription);

	// Show the modal
	$('#editDepartmentModal').modal('show');
}

function resetEditForm() {
	// Reset to original values
	$('#editDepartmentId').val(originalFormData.departmentId);
	$('#editDepartmentName').val(originalFormData.departmentName);
	$('#editDepartmentDescription').val(originalFormData.description);
}

// Handle form submission
$('#editDepartmentForm').submit(function(e) {
	e.preventDefault();

	const formData = {
		departmentId: $('#editDepartmentId').val(),
		departmentName: $('#editDepartmentName').val(),
		description: $('#editDepartmentDescription').val()
	};

	// Show loading state
	const submitBtn = $(this).find('button[type="submit"]');
	const originalText = submitBtn.text();
	submitBtn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Updating...');

	// Send AJAX request
	$.ajax({
		type: 'POST',
		url: 'updateDepartment',
		data: formData,
		success: function(response) {
			if (response.success) {
				// Show success message
				Swal.fire({
					title: 'Success!',
					text: 'Department updated successfully',
					icon: 'success',
					timer: 1500,
					showConfirmButton: false
				}).then(() => {
					// Close modal and refresh page
					$('#editDepartmentModal').modal('hide');
					location.reload();
				});
			} else {
				// Show error message
				Swal.fire({
					title: 'Error!',
					text: response.message || 'Failed to update department',
					icon: 'error'
				});
			}
		},
		error: function(xhr, status, error) {
			// Show error message
			Swal.fire({
				title: 'Error!',
				text: 'Failed to update department',
				icon: 'error'
			});
		},
		complete: function() {
			// Reset button state
			submitBtn.prop('disabled', false).text(originalText);
		}
	});
});

// Add modal event listeners
$('#editDepartmentModal').on('hidden.bs.modal', function() {
	// Reset form when modal is closed
	resetEditForm();
});

// Add input validation
$('#editDepartmentName').on('input', function() {
	if (!$(this).val().trim()) {
		$(this).addClass('is-invalid');
		$(this).next('.invalid-feedback').remove();
		$(this).after('<div class="invalid-feedback">Department name is required</div>');
	} else {
		$(this).removeClass('is-invalid');
		$(this).next('.invalid-feedback').remove();
	}
});

//edit role function
let originalRoleData = {};

function showEditRoleModal(roleId, roleName, roleDescription) {
    // Store original values
    originalRoleData = {
        roleId: roleId,
        roleName: roleName,
        roleDescription: roleDescription
    };

    // Populate the form
    $('#editRoleId').val(roleId);
    $('#editRoleName').val(roleName);
    $('#editRoleDescription').val(roleDescription);

    // Show the modal
    $('#editRoleModal').modal('show');
}

function resetRoleEditForm() {
    // Reset to original values
    $('#editRoleId').val(originalRoleData.roleId);
    $('#editRoleName').val(originalRoleData.roleName);
    $('#editRoleDescription').val(originalRoleData.roleDescription);
}

// Handle form submission
$('#editRoleForm').submit(function(e) {
    e.preventDefault();
    
    const formData = {
        roleId: $('#editRoleId').val(),
        roleName: $('#editRoleName').val(),
        roleDescription: $('#editRoleDescription').val()
    };

    // Show loading state
    const submitBtn = $(this).find('button[type="submit"]');
    const originalText = submitBtn.text();
    submitBtn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Updating...');

    // Send AJAX request
    $.ajax({
        type: 'POST',
        url: 'updateRole',
        data: formData,
        success: function(response) {
			console.log(response);
            if (response.success) {
                // Show success message
                Swal.fire({
                    title: 'Success!',
                    text: 'Role updated successfully',
                    icon: 'success',
                    timer: 1500,
                    showConfirmButton: false
                }).then(() => {
                    // Close modal and refresh page
                    $('#editRoleModal').modal('hide');
                    location.reload();
                });
            } else {
                // Show error message
                Swal.fire({
                    title: 'Error!',
                    text: response.message || 'Failed to update role',
                    icon: 'error'
                });
            }
        },
        error: function(xhr, status, error) {
            // Show error message
            Swal.fire({
                title: 'Error!',
                text: 'Failed to update role',
                icon: 'error'
            });
        },
        complete: function() {
            // Reset button state
            submitBtn.prop('disabled', false).text(originalText);
        }
    });
});

// Delete role function
function deleteRole(roleId) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'POST',
                url: 'deleteRole',
                data: { roleId: roleId },
                success: function(response) {
                    if (response.success) {
                        Swal.fire(
                            'Deleted!',
                            'Role has been deleted.',
                            'success'
                        ).then(() => {
                            location.reload();
                        });
                    } else {
                        Swal.fire(
                            'Error!',
                            response.message || 'Failed to delete role',
                            'error'
                        );
                    }
                },
                error: function() {
                    Swal.fire(
                        'Error!',
                        'Failed to delete role',
                        'error'
                    );
                }
            });
        }
    });
}

// Add modal event listeners
$('#editRoleModal').on('hidden.bs.modal', function () {
    // Reset form when modal is closed
    resetRoleEditForm();
});

// Add input validation
$('#editRoleName').on('input', function() {
    if (!$(this).val().trim()) {
        $(this).addClass('is-invalid');
        $(this).next('.invalid-feedback').remove();
        $(this).after('<div class="invalid-feedback">Role name is required</div>');
    } else {
        $(this).removeClass('is-invalid');
        $(this).next('.invalid-feedback').remove();
    }
});
