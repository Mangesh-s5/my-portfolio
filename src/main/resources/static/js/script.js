const roles = ['Java Full Stack Developer', 'Spring Boot Engineer', 'AI Engineering Student'];
let currentRoleIndex = 0;
let currentCharIndex = 0;
let isDeleting = false;
const typingSpeed = 100;
const deletingSpeed = 50;
const delayBetweenRoles = 2000;

function typeEffect() {
    const currentRole = roles[currentRoleIndex];
    const typingElement = document.getElementById('typing');
    
    if (isDeleting) {
        // Remove characters
        typingElement.innerText = currentRole.substring(0, currentCharIndex - 1);
        currentCharIndex--;
    } else {
        // Add characters
        typingElement.innerText = currentRole.substring(0, currentCharIndex + 1);
        currentCharIndex++;
    }

    // Determine speed variation
    let activeSpeed = isDeleting ? deletingSpeed : typingSpeed;

    if (!isDeleting && currentCharIndex === currentRole.length) {
        // Pause at the complete word
        activeSpeed = delayBetweenRoles;
        isDeleting = true;
    } else if (isDeleting && currentCharIndex === 0) {
        isDeleting = false;
        // Shift to next index sequence
        currentRoleIndex = (currentRoleIndex + 1) % roles.length;
        activeSpeed = 500;
    }

    setTimeout(typeEffect, activeSpeed);
}

// Initial Call
document.addEventListener('DOMContentLoaded', () => {
    typeEffect();
});

// Optimized Theme Switcher Engine 
document.getElementById('themeBtn').onclick = () => {
    document.body.classList.toggle('light');
};